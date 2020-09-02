package com.sample.dl.bdd.cucumber.WS.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        tags = {"@WS_Regression"},
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/WS/features"},
        strict = true,
        plugin = {
                "json:target/result.json",
                "com.epam.reportportal.cucumber.StepReporter"
        },
        glue = {
                "com.sample.dl.bdd.cucumber.WS.hooks",
                "com.sample.dl.bdd.cucumber.WS.steps"
        }
)

public class WSRunnerReportIO extends AbstractTestNGCucumberTests {
}
