package com.sample.dl.bdd.utils.db

import org.apache.poi.ss.formula.functions.T
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource;

@Component
class DBManager {
    private JdbcTemplate jdbcTemplate

    @Autowired
    DBManager(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * General execute sql
     * @param statement SQL statement
     * */
    def execute(String statement) {
        jdbcTemplate.execute(statement)
    }


    /**
     * Execute query sql
     * @param statement SQL statement
     * @return List<Map<String, Object> >
     * */
    def queryData(String statement) throws DataAccessException{
        return jdbcTemplate.queryForList(statement)
    }


    /**
     * Execute query sql and then map to Object
     * @param statement SQL statement
     * @return List<Map<String, Object> >
     * */
    def queryForObject(String statement, Class<T> valueType) throws DataAccessException{
        return jdbcTemplate.queryForObject(statement, valueType)
    }


    /**
     * Execute query sql and then map to Object
     * @param statement SQL statement
     * @return List<Map<String, Object> >
     * */
    def queryForList(String statement, Class<T> valueType) throws DataAccessException{
        return jdbcTemplate.queryForList(statement, valueType)
    }
}
