package com.sample.dl.bdd.cucumber.UI.hooks;

import com.sample.dl.bdd.cucumber.UI.configuration.UIConfiguration;
import com.sample.dl.bdd.cucumber.UI.pages.PageAction;
import com.sample.dl.contexts.AppContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
//import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@ContextConfiguration(classes = {AppContext.class})
//@DirtiesContext

@Scope("cucumber-glue")
public class UIHooks {

    @Autowired
    WebDriver driver;

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
    public void tearDownScenario(){
        pageAction.closeBrowser();
    }
}


