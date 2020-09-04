package com.sample.dl.bdd.utils.drivers

import com.sample.dl.bdd.utils.common.ConfigHandler
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

import java.util.concurrent.TimeUnit

class WebDriverFactory {
    private static ConfigHandler config;
    private static String browserType;
    private static WebDriver driver;

    static WebDriver getDriver() {
        config = new ConfigHandler()
        WebDriver methodLocalDriver
        browserType = config.getBrowserType()
        String gridHubServer = config.getGridHubServer();

        if ((gridHubServer != null) && !gridHubServer.isEmpty()) {
            methodLocalDriver = getRemoteDriver();
        } else {
            methodLocalDriver = getLocalDriver();
        }
        return methodLocalDriver;
    }

    private static void setBrowserTimeouts() {
        // Page load timeout
        int timeout = config.getBrowserPageLoadTimeout();
//        logger.info(String.format("Set browser page load timeout to %d", requested));
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);

        // Script timeout
        timeout = config.getBrowserScriptTimeout();
//        logger.info(String.format("Set browser script timeout to %d", requested));
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS)
    }


    private static WebDriver getLocalDriver() {
////        logger.info("Creating local " + browserType + " Webdriver");
//        WebDriver methodLocalDriver;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (browserType) {
            case "CHROME":
                Object options = DriverOptions.setChromeProfile()
                capabilities.setCapability(ChromeOptions.CAPABILITY, options)
                driver = DriverManager.getChrome()
                break;

            default:
                Object options = DriverOptions.setFirefoxProfile()
                if (options != null) {
                    capabilities.setCapability("firefoxOptions", options)
                }
                driver = DriverManager.getFirefox()
                break;
        }
        setBrowserTimeouts()
        return driver
    }

    private static WebDriver getRemoteDriver() {
//        WebDriver remoteDriver = null;
        String gridHubUrl;
        if (config.getGridHubServer().split(":").length == 2) {
            gridHubUrl = String.format("http://%s/wd/hub", config.getGridHubServer())
        } else {
            gridHubUrl = String.format("http://%s:4444/wd/hub", config.getGridHubServer());
        }

//        logger.info("Creating remote " + browserType + " Webdriver on " + gridHubUrl);
        DesiredCapabilities capabilities;

        // Set up browser specific capabilities
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
                if (options != null) {
                    capabilities.setCapability(FirefoxDriver.PROFILE, options)
                }
                break;
        }


        // Add any capabilities across all browsers
        capabilities.setJavascriptEnabled(true)

        // create the driver
        try {
            driver = new RemoteWebDriver(new URL(gridHubUrl), capabilities)
        } catch (final MalformedURLException e) {
//            logger.error(e.getStackTrace());
        }
        setBrowserTimeouts()
        return driver;
    }

}
