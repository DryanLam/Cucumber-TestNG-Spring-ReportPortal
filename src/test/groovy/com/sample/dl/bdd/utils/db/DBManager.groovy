package com.sample.dl.bdd.utils.db

import org.apache.poi.ss.formula.functions.T
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource;

@Component
class DBManager {
//    @Autowired
//    JdbcTemplate jdbcTemplate

    private JdbcTemplate jdbcTemplate

    @Autowired
    DBManager(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    def execute(String statement){
        return jdbcTemplate.execute(statement)
    }

    def queryData(String statement){
        return jdbcTemplate.queryForList(statement)
    }

    def queryForObject(String statement, Class<T> valueType){
        return jdbcTemplate.queryForObject(statement, valueType)
    }

    def queryForList(String statement, Class<T> valueType){
        return jdbcTemplate.queryForList(statement, valueType)
    }


}
