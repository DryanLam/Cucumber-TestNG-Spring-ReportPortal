package com.sample.dl.bdd.cucumber.UI.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@PropertySource("classpath:UI/${environment}.properties")
@Configuration
@PropertySource({
        "classpath:UI/UI-Endpoints.properties",
        "classpath:config.properties"
})
public class UIConfiguration {

    @Value("${ui.app.url}")
    private String appEndpoint;

    public String getEndpoint() {
        return appEndpoint;
    }
}
