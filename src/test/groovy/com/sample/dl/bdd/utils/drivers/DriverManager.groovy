package com.sample.dl.bdd.utils.drivers

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.firefox.FirefoxDriver

class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver() {
//    static WebDriver getDriver() {
//        if (driver?.sessionId == null || driver == null) {
            driver = getChrome()
//        }
        return driver
    }


    private static def getChrome() {
        WebDriverManager.chromedriver().setup()
        return new ChromeDriver()
    }

    private static def getFirefox() {
        WebDriverManager.firefoxdriver().setup()
        return new FirefoxDriver()
    }
}
