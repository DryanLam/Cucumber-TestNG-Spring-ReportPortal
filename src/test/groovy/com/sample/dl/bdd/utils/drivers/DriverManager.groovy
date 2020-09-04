package com.sample.dl.bdd.utils.drivers

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.firefox.FirefoxDriver

class DriverManager {

    static def getChrome() {
        WebDriverManager.chromedriver().setup()
        return new ChromeDriver()
    }

    static def getFirefox() {
        WebDriverManager.firefoxdriver().setup()
        return new FirefoxDriver()
    }
}
