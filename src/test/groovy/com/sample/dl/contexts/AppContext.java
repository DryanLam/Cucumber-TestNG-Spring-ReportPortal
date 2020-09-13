package com.sample.dl.contexts;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

//@ComponentScan({"com.sample.dl.bdd","com.sample.dl.contexts.annotations"})
@Configuration
@ComponentScan(basePackages = {
        "com.sample.dl.bdd.cucumber.UI",
        "com.sample.dl.contexts.dbconfig",
        "com.sample.dl.contexts.annotations",
        "com.sample.dl.bdd.utils"})
public class AppContext {
    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
