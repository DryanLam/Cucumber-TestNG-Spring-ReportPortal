package com.sample.dl.bdd.cucumber.UI.runner;

import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        tags = {" @UI_Browsers"},
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/UI/features"},
        monochrome = true,
        strict = true,
        plugin = {
                "json:target/result.json",
                "com.epam.reportportal.cucumber.StepReporter"
        },
        glue = {
                "com.sample.dl.bdd.cucumber.UI.hooks",
                "com.sample.dl.bdd.cucumber.UI.steps"
        }
)

public class UIRunnerReportIO extends AbstractTestNGCucumberTests {

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
