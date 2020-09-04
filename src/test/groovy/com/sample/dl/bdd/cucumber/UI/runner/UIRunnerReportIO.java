package com.sample.dl.bdd.cucumber.UI.runner;

import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        monochrome = true,
        strict = true,
        glue = {
                "com.sample.dl.bdd.cucumber.UI.hooks",
                "com.sample.dl.bdd.cucumber.UI.steps"
        },
        plugin = {
                "json:target/result.json",
                "com.epam.reportportal.cucumber.StepReporter"
        },
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/UI/features"},
        tags = {" @UI_Browsers"}
)

public class UIRunnerReportIO extends AbstractTestNGCucumberTests {

        @BeforeSuite()
        public void beforeSuite(){
                LogManager.setLogLevel();
        }
}
