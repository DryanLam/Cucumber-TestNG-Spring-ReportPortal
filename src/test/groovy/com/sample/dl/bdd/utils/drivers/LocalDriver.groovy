package com.sample.dl.bdd.utils.drivers

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Capabilities
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

class LocalDriver {

    static def getChrome(Capabilities cap = null) {
        WebDriverManager.chromedriver().setup()
        return new ChromeDriver(cap)
    }

    static def getFirefox(Capabilities cap = null) {
        WebDriverManager.firefoxdriver().setup()
        return new FirefoxDriver(cap)
    }
}
