package com.sample.dl.bdd.utils.drivers

import com.sample.dl.bdd.utils.common.ConfigHandler
import groovy.util.logging.Slf4j
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.SessionId
import org.springframework.stereotype.Service

import java.util.concurrent.TimeUnit

@Slf4j
@Service
class WebDriverFactory {
    private ConfigHandler config
    private String browserType
    private WebDriver driver

    WebDriver createDriver() {
        config = new ConfigHandler()
        browserType = config.getBrowserType()
        String gridHubServer = config.getGridHubServer()

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
        // Page loads timeout
        int timeout = config.getBrowserPageLoadTimeout()
        log.info(String.format("Set browser page load timeout to %d", timeout))
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS)
        // Script timeout
        timeout = config.getBrowserScriptTimeout();
        log.info(String.format("Set browser script timeout to %d", timeout))
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS)
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
        if (config.getGridHubServer().split(":").length == 2) {
            gridHubUrl = String.format("http://%s/wd/hub", config.getGridHubServer())
        } else {
            gridHubUrl = String.format("http://%s:4444/wd/hub", config.getGridHubServer())
        }
        log.info("Creating remote " + browserType + " WebDriver on " + gridHubUrl)

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

        try {
            driver = new RemoteWebDriver(new URL(gridHubUrl), capabilities)
        } catch (final MalformedURLException e) {
            log.error(e.getStackTrace());
        }
        setBrowserTimeouts(driver)
        return driver
    }
}
