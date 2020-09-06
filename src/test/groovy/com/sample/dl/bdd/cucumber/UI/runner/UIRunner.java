package com.sample.dl.bdd.cucumber.UI.runner;

import com.sample.dl.bdd.utils.common.DataDrivenHandler;
import com.sample.dl.bdd.utils.common.LogManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        monochrome = true,
        plugin = {
                "json:target/result.json",
        },
        glue = {
                "com.sample.dl.bdd.cucumber.UI.hooks",
                "com.sample.dl.bdd.cucumber.UI.steps"
        },
        features = {"src/test/groovy/com/sample/dl/bdd/cucumber/UI/features"},
        tags = {" @UI_Browsers_01"}
)
public class UIRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite()
    public void beforeSuite() {
        LogManager.setLogLevel();

        DataDrivenHandler data = DataDrivenHandler.getInstance();
        data.getValue("$..email");
    }

    @AfterSuite()
    public void AfterSuite() {
    }
}
