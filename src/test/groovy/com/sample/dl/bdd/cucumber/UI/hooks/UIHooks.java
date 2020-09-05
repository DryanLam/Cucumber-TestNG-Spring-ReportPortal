package com.sample.dl.bdd.cucumber.UI.hooks;

import com.sample.dl.bdd.cucumber.UI.configuration.UIConfiguration;
import com.sample.dl.bdd.cucumber.UI.pages.PageAction;
import com.sample.dl.contexts.AppContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;

//@DirtiesContext


@ContextConfiguration(classes = {AppContext.class})
public class UIHooks {

    @Autowired
    UIConfiguration configUI;

    @Autowired
    PageAction pageAction;

    @Before
    public void setUpScenario() {
//        ReportService.resetService();
        pageAction.openBrowser(configUI.getEndpoint());
    }

    @After
    public void tearDownScenario(Scenario scenario){
        tearDown(scenario);
        pageAction.closeBrowser();
    }

    private void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = pageAction.screenShot();
            scenario.embed(screenshot, "image/png");
        }
    }
}


