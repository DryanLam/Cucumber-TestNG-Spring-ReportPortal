package com.sample.dl.bdd.cucumber.UI.runner;

import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

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
    public void beforeSuite() {
        LogManager.setLogLevel();

        LogManager.info("====================================================");
        LogManager.info("             Start UI Automation Test               ");
        LogManager.info("====================================================");
    }

    @AfterSuite()
    public void AfterSuite() {
        LogManager.info("====================================================");
        LogManager.info("             End UI Automation Test                 ");
        LogManager.info("====================================================");
    }
}
