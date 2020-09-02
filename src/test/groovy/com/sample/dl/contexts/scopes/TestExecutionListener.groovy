package com.sample.dl.contexts.scopes;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

class TestExecutionListener extends AbstractTestExecutionListener {
    @Override
    void beforeTestMethod(TestContext testContext) throws Exception {
        testContext.getApplicationContext().getBean(TestScope.class).reset();
    }

    @Override
    void afterTestMethod(TestContext testContext) throws Exception {
        testContext.getApplicationContext().getBean(WebDriver.class).quit();
    }
}
