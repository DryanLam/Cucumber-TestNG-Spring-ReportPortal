package com.sample.dl.bdd.cucumber.UI.runner;

import com.sample.dl.bdd.utils.common.LogManager;
import com.sample.dl.contexts.scopes.TestExecutionListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@TestExecutionListeners({ TestExecutionListener.class,
                          DependencyInjectionTestExecutionListener.class })
@CucumberOptions(
//        tags = {"@UI,@steptest"}, // OR
//        tags = {"@UI","@API"},// AND
        tags = {" @UI_Browsers"},
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/UI/features"},
//        monochrome = true, // Much more readable
        strict = true, // Fail: Mark build success if build contains failed cases
        plugin = {
                "json:target/result.json",
        },
//        dryRun = true,
        glue = {
                "com.sample.dl.bdd.cucumber.UI.hooks",
                "com.sample.dl.bdd.cucumber.UI.steps"
        }
)

public class UIRunner extends AbstractTestNGCucumberTests {

        @BeforeSuite()
        public void beforeSuite(){
                LogManager.setLogLevel();

                System.out.println("====================================================");
                System.out.println("             Start UI Automation Test               ");
                System.out.println("====================================================");
        }

        @AfterSuite()
        public void AfterSuite(){
                System.out.println("====================================================");
                System.out.println("             End UI Automation Test                 ");
                System.out.println("====================================================");
        }
}
