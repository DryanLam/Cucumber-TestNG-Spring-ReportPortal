package com.sample.dl.bdd.utils.db

import org.apache.poi.ss.formula.functions.T
import org.springframework.beans.factory.annotation.Autowired
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

    def queryData(String statement){
       return jdbcTemplate.execute(statement)
    }

    def queryForObject(String statement, Class<T> valueType){
        return jdbcTemplate.queryForObject(statement, valueType)
    }

    def queryForList(String statement){
        return jdbcTemplate.queryForList(statement)
    }


}
