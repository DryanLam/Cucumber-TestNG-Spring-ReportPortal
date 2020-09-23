package com.sample.dl.bdd.cucumber.WS.hooks

import com.sample.dl.bdd.cucumber.WS.configuration.AppGateway
import com.sample.dl.contexts.WSContext
import groovy.util.logging.Slf4j
import io.cucumber.core.api.Scenario
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.BeforeStep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = WSContext.class)
@Slf4j
class WSHooks {

    @Autowired
    AppGateway gateway;

    @Before
    void setUpScenario(Scenario sc) {
        def tags = sc.getSourceTagNames()join(",");
        gateway.setHeader(["testcase": tags])
        log.info("Test WS");
    }

    @After
    void tearDownScenario(Scenario sc){

    }

    @BeforeStep
    void handleInterceptor(){
        Thread.sleep(1250);
    }
}

