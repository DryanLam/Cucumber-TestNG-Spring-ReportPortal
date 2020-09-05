package com.sample.dl.contexts;

import com.sample.dl.bdd.utils.drivers.WebDriverFactory;
import com.sample.dl.contexts.scopes.TestScope;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Create scope:test
    @Bean
    public CustomScopeConfigurer customScopeConfig() {
        CustomScopeConfigurer scopeConfig = new CustomScopeConfigurer();
        scopeConfig.addScope("test", new TestScope());
        return scopeConfig;
    }

    @Scope("test")
    @Bean
    public WebDriver getDriver() {
        WebDriverFactory wF = new WebDriverFactory();
        return wF.getDriver();
    }
}
