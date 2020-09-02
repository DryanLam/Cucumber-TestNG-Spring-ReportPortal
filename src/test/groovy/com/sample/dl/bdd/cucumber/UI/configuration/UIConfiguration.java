package com.sample.dl.bdd.cucumber.UI.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:UI/${environment}.properties")
@Configuration
@PropertySource("classpath:UI/UI-Endpoints.properties")
public class UIConfiguration {

    @Value("${ui.app.url}")
    private String appEndpoint;

    @Value("${ui.log.level}")
    private String logLevel;

    public String getEndpoint() {
        return appEndpoint;
    }
    public String getLogLevel() {
        return logLevel;
    }


}
