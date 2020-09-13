package com.sample.dl.contexts;

import com.sample.dl.contexts.annotations.IgnoreScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {
        "com.sample.dl.bdd.cucumber.WS",
        "com.sample.dl.contexts.dbconfig",
        "com.sample.dl.bdd.utils"
        },
               excludeFilters = @ComponentScan.Filter(IgnoreScan.class))
public class WSContext {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
