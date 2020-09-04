package com.sample.dl.bdd.cucumber.UI.pages


import com.sample.dl.bdd.utils.common.Timeouts
import com.sample.dl.bdd.utils.drivers.WebDriverFactory
import com.sample.dl.contexts.annotations.PageObject
import groovy.util.logging.Slf4j
import org.openqa.selenium.*
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

import javax.annotation.PreDestroy
import java.util.concurrent.TimeUnit

@Component
@Slf4j
class PageAction {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    WebDriverFactory driverFactory;

    protected WebDriver driver
    protected static WebDriverWait wait

    def initPageFactory(){
        driver = driverFactory.getDriver()
        def beans = appContext.getBeansWithAnnotation(PageObject.class);
        beans.each { bean -> PageFactory.initElements(driver, bean.value)}
        println(beans.toString())
    }

    def openBrowser(String url){
        wait = new WebDriverWait(driver,Timeouts.LONG_TIME)
        navigateTo(url)
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(Timeouts.MEDIUM_TIME, TimeUnit.SECONDS);
    }

    @PreDestroy
    def closeBrowser(){
        driver.quit()
    }

    def navigateTo(String url){
        driver.navigate().to(url)
        return new PageAction()
    }

    def refreshPage() {
        driver.navigate().refresh()
    }

    def click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollIntoView(element)
        element.click()
        waitForPageLoad()
    }

    def isDisplayed(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element))
    }

    def getText(WebElement element){
        String value = element.getText().trim()
        log.info(value)
        return value
    }

    def executeJS(Object... value) {
        JavascriptExecutor executor = ((driver) as JavascriptExecutor)
        if (value.size() < 2) {
            return executor.executeScript("return ${value};")
        }else if(value.size() == 2){
            executor.executeScript(value.first(),value.last())
        }
    }

    def scrollIntoView(def element){
        executeJS('arguments[0].scrollIntoView(true)',element)
    }

    def waitForPageLoad() {
        pause(Timeouts.DEFAULT_TIME)
        ExpectedCondition<Boolean> pageLoadCompleted = new ExpectedCondition<Boolean>() {
            @Override
            Boolean apply(WebDriver _driver) {
                    return (Boolean) ((JavascriptExecutor) _driver).executeScript("return document.readyState")
                                                                    .toString()
                                                                    .equalsIgnoreCase("complete")
            }
        }
        return wait.until(pageLoadCompleted)
    }

    def pause(int time){
        Thread.sleep(time*1000)
    }

    byte[] screenShot(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
    }

}
