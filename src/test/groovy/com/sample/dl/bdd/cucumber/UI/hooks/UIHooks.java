package com.sample.dl.bdd.cucumber.UI.hooks;

import com.sample.dl.bdd.cucumber.UI.configuration.UIConfiguration;
import com.sample.dl.bdd.cucumber.UI.pages.PageAction;
import com.sample.dl.bdd.utils.db.DBManager;
import com.sample.dl.contexts.AppContext;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {AppContext.class})
public class UIHooks {

    @Autowired
    DBManager db;

    @Autowired
    UIConfiguration configUI;

    @Autowired
    PageAction pageAction;

    @Before
    public void setUpScenario() {
        pageAction.initPageFactory();
        pageAction.openBrowser(configUI.getEndpoint());
    }

    @After
    public void tearDownScenario(Scenario scenario){
        tearDown(scenario);
    }

    private void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = pageAction.screenShot();
            scenario.embed(screenshot, "image/png");
        }
        pageAction.closeBrowser();
    }
}


