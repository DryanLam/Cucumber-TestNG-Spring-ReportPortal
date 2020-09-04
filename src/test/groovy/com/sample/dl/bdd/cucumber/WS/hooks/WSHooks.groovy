package com.sample.dl.bdd.cucumber.WS.hooks;

import com.sample.dl.contexts.WSContext;
import groovy.util.logging.Slf4j;
import io.cucumber.java.Before;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = WSContext.class)
@Slf4j
class WSHooks {

    @Before
    void setUpScenario() {
        log.info("Test WS");
    }
}

