package com.sample.dl.bdd.cucumber.WS.runner;

import com.sample.dl.bdd.utils.common.ConfigHandler;
import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
//        tags = {"@WS_Regression"},
        tags = {"@Excel"},
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/WS/features"},
        strict = true,
        plugin = {
                "json:target/result.json",
        },
        glue = {
                "com.sample.dl.bdd.cucumber.WS.hooks",
                "com.sample.dl.bdd.cucumber.WS.steps"
        }
)

public class WSRunner extends AbstractTestNGCucumberTests {
        @BeforeSuite()
        public void beforeSuite(){
                LogManager.setLogLevel();

                System.out.println("====================================================");
                System.out.println("             Start UI Automation Test               ");
                System.out.println("====================================================");
        }
}
