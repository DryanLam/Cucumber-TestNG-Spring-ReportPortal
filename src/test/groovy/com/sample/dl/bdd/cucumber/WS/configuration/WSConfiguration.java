package com.sample.dl.bdd.cucumber.WS.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:WS/WS-Endpoints.properties")
public class WSConfiguration {

    @Value("${ws.app.url}")
    private String appEndpoint;

    @Value("${endpoint.ws.users}")
    public String lstUser;

    public String getEndpoint() {
        return appEndpoint;
    }

    public void setEndpoint(String endpoint) {
        this.appEndpoint = appEndpoint;
    }
}
