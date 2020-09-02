package com.sample.dl.bdd.cucumber.UI.pages

import com.sample.dl.bdd.utils.common.LogManager
import com.sample.dl.bdd.utils.common.Timeouts
import groovy.util.logging.Slf4j
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
@Slf4j
class PageAction {

    @Autowired
    protected WebDriver driver

    protected static WebDriverWait wait

    def openBrowser(String url){
        wait = new WebDriverWait(driver,Timeouts.LONG_TIME)
        navigateTo(url)
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(Timeouts.MEDIUM_TIME, TimeUnit.SECONDS);
    }

    def closeBrowser(){
        driver.close()
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



//    def waitForPageLoad(){
//        def currentTime = Calendar.getInstance().getTime()
//        def endTime, count = 0
//        use(TimeCategory) {	endTime = currentTime + timeOut.seconds	}
//        def wait = true
//        while (wait && !currentTime.after(endTime))
//        {
//            pauseToWait(1)
//            def isCompleted = executeJavasSript("jQuery.active == 0",FailureHandling.OPTIONAL).toBoolean()
//            if (isCompleted){
//                wait = false
//            }
//            currentTime = Calendar.getInstance().getTime()
//        }
//    }
//
//    public boolean waitForJSandJQueryToLoad() {
//
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//
//        // wait for jQuery to load
//        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver ldriver) {
//                try {
//                    return ((Long)((JavascriptExecutor)ldriver()).executeScript("return jQuery.active") == 0);
//                }
//                catch (Exception e) {
//                    // no jQuery present
//                    return true;
//                }
//            }
//        };
//
//        // wait for Javascript to load
//        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver ldriver) {
//                return ((JavascriptExecutor)ldriver()).executeScript("return document.readyState")
//                        .toString().equals("complete");
//            }
//        };
//
//        return wait.until(jQueryLoad) && wait.until(jsLoad);
//    }

    def waitForPageLoad() {
        pause(Timeouts.SHORT_TIME)
        ExpectedCondition<Boolean> pageLoadCompleted = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver _driver) {
                    return (Boolean) ((JavascriptExecutor) _driver).executeScript("return document.readyState")
                                                                    .toString()
                                                                    .equalsIgnoreCase("complete")
            }
        }
        return wait.until(pageLoadCompleted)
    }

    def pause(int time){
        Thread.sleep(time)
    }

//    boolean waitForPageLoad() {
//        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver _driver) {
//                try {
//                    return (Boolean) ((JavascriptExecutor) _driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
//                }
//                catch (Exception e) {
//                    // no jQuery present
//                    return true;
//                }
//            }
//        };
//        return wait.until(jQueryLoad)
//    }



//    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
//        return new ExpectedCondition<Boolean>() {
//
//            @Override
//            public Boolean apply(WebDriver driver) {
//                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
//            }
//        };
//    }
}
