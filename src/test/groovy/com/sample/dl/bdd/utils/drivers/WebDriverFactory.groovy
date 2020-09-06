package com.sample.dl.bdd.utils.drivers


import com.sample.dl.bdd.utils.exceptions.TestException
import groovy.util.logging.Slf4j
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.SessionId
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import java.util.concurrent.TimeUnit

@Slf4j
@Service
class WebDriverFactory {

    @Value('${browser.test.type}')
    private String browserType;

    @Value('${remote.server.hub}')
    private String gridHubServer;

    @Value('${browser.page.timeout}')
    private String pageLoadTimeout

    @Value('${browser.script.timeout}')
    private String scriptTimeout

    private WebDriver driver

    WebDriver createDriver() {
        if ((gridHubServer != null) && !gridHubServer.isEmpty()) {
            driver = getRemoteDriver()
        } else {
            driver = getLocalDriver()
        }
        return driver
    }

    WebDriver getDriver() {
        if (!isValidDriver(driver)) {
            driver = createDriver()
        }
        return driver
    }

    boolean isValidDriver(WebDriver driver) {
        SessionId session
        if (driver) {
            session = ((RemoteWebDriver) driver).getSessionId();
        }
        return driver && session
    }

    void setBrowserTimeouts(WebDriver driver) {
        log.info("Set page load timeout to ${pageLoadTimeout}")
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout.toInteger(), TimeUnit.SECONDS)
        log.info("Set browser script timeout to ${scriptTimeout}")
        driver.manage().timeouts().setScriptTimeout(scriptTimeout.toInteger(), TimeUnit.SECONDS)
    }


    private WebDriver getLocalDriver() {
        log.info("Creating local " + browserType + " Webdriver")

        WebDriver driver
        DesiredCapabilities capabilities = new DesiredCapabilities()

        switch (browserType) {
            case "CHROME":
                Object options = DriverOptions.setChromeProfile()
                capabilities.setCapability(ChromeOptions.CAPABILITY, options)
                driver = LocalDriver.getChrome(capabilities)
                break;

            default:
                Object options = DriverOptions.setFirefoxProfile()
                if (options != null) {
                    capabilities.setCapability("firefoxOptions", options)
                }
                driver = LocalDriver.getFirefox(capabilities)
                break;
        }
        setBrowserTimeouts(driver)
        return driver
    }

    private WebDriver getRemoteDriver() {
        String gridHubUrl
        DesiredCapabilities capabilities
        WebDriver driver

        switch (gridHubServer.split(":").length) {
            case 1: gridHubUrl = String.format("http://%s:4444/wd/hub", gridHubServer); break
            case 2: gridHubUrl = String.format("http://%s/wd/hub", gridHubServer); break
            default: throw new TestException("---Error: Invalid Remote Url");
        }
        log.info("Creating remote " + browserType + " WebDriver on " + gridHubUrl)

        // Init brow options
        switch (browserType) {
            case "CHROME":
                Object options = DriverOptions.setChromeProfile()
                capabilities = DesiredCapabilities.chrome()
                if (options != null) {
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options)
                }
                break;

            default:
                Object options = DriverOptions.setFirefoxProfile()
                capabilities = DesiredCapabilities.firefox()
                if (options) {
                    capabilities.setCapability(FirefoxDriver.PROFILE, options)
                }
                break;
        }

        // Add any capabilities across all browsers
        capabilities.setJavascriptEnabled(true)

        // Start new remote driver
        try {
            driver = new RemoteWebDriver(new URL(gridHubUrl), capabilities)
            setBrowserTimeouts(driver)
        } catch (e) {
            log.error(e.message);
            throw new TestException(e)
        }
        return driver
    }
}
