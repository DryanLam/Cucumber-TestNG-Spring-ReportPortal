package com.sample.dl.bdd.cucumber.WS.hooks;

import com.sample.dl.contexts.WSContext;
import io.cucumber.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = WSContext.class)
public class WSHooks {

    @Before
    public void setUpScenario() {
        System.out.println("Test WS");
    }

}

