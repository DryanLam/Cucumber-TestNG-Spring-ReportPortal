package com.sample.dl.contexts.dbconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource({"classpath:config.properties"})
public class DBConfiguration {

    @Autowired
    Environment env;

    private final String URL = "db.connect.url";
    private final String DRIVER = "db.connect.driver";
    private final String USER = "db.connect.dbuser";
    private final String PASSWORD = "db.connect.dbpassword";

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverDataSource = new DriverManagerDataSource();
        driverDataSource.setUrl(env.getProperty(URL));
        driverDataSource.setUsername(env.getProperty(USER));
        driverDataSource.setPassword(env.getProperty(PASSWORD));
        driverDataSource.setDriverClassName(env.getProperty(DRIVER));
        return driverDataSource;
    }
}
