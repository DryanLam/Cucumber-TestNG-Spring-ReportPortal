package com.sample.dl.contexts;

import com.sample.dl.bdd.utils.drivers.WebDriverFactory;
import com.sample.dl.contexts.scopes.TestScope;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
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

    private WebDriver abc;

    // Create scope:test
    @Bean
    public CustomScopeConfigurer customScopeConfig() {
        CustomScopeConfigurer scopeConfig = new CustomScopeConfigurer();
        scopeConfig.addScope("cucumber-glue", new TestScope());
        return scopeConfig;
    }

    @Scope("cucumber-glue")
    @Bean(name = "webdriver", destroyMethod = "quit")
    public WebDriver getDriver() {
        WebDriverFactory wF = new WebDriverFactory();

//        if (abc == null) {
//            abc = wF.getDriver();
//        } else {
//            SessionId sessionid = ((RemoteWebDriver) abc).getSessionId();
//            System.out.println(sessionid);
//            if(sessionid == null){
//                return wF.getDriver();
//            }
//        }
//        return abc;
        return wF.getDriver();
    }
}
