package com.sample.dl.bdd.cucumber.WS.runner;

import com.sample.dl.bdd.utils.common.ConfigHandler;
import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        strict = true,
        plugin = {
                "json:target/result.json",
        },
        glue = {
                "com.sample.dl.bdd.cucumber.WS.hooks",
                "com.sample.dl.bdd.cucumber.WS.steps"
        },
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/WS/features"},
        tags = {"@Agent"}
//        tags = {"@API","@ID-1001"}
)

public class WSRunner extends AbstractTestNGCucumberTests {

        @BeforeSuite()
        public void beforeSuite(){
                LogManager.setLogLevel();
        }
}
