package com.sample.dl.contexts;

import com.sample.dl.bdd.utils.drivers.DriverManager;
import com.sample.dl.contexts.scopes.TestScope;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import java.util.HashMap;
import java.util.Map;

//@ComponentScan({"com.sample.dl.bdd","com.sample.dl.contexts.annotations"})
@Configuration
@ComponentScan(basePackages = {"com.sample.dl.bdd.cucumber.UI",
                               "com.sample.dl.contexts.annotations",
                               "com.sample.dl.bdd.utils"})
public class AppContext {


    // TestScope support to clear the cache before each test run
    @Bean
    public TestScope testScope() {
        return new TestScope();
    }

    // Create scope:test
    @Bean
    public CustomScopeConfigurer customScopeConfig() {
        CustomScopeConfigurer scopeConfig = new CustomScopeConfigurer();
        Map<String, Object> scopes = new HashMap<>();
        scopes.put("test", testScope());
        scopeConfig.setScopes(scopes);
        return scopeConfig;
    }

    @Bean
    @Scope("test")
    public WebDriver getWebDriver() {
        return DriverManager.getDriver();
    }
}
