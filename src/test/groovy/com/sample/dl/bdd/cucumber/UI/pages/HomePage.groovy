package com.sample.dl.bdd.cucumber.UI.pages

import com.sample.dl.contexts.annotations.PageObject
import groovy.util.logging.Slf4j
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.How
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

@PageObject
@Slf4j
class HomePage{

    @Autowired
    PageAction pageAction

    @Autowired
    private Environment env;

    @FindBy(how = How.CSS, using = ".v-center h3")
    private List<WebElement> keySells

    @FindBy(how = How.CSS, using = ".tagline:first-of-type")
    private WebElement tagLineTitle

    @FindBy(css = ".request-title .url")
    private WebElement requestPath

    @FindBy(css = ".response-code")
    private WebElement responseCode

    @FindBy(css = "[data-id='users-single-not-found']")
    private WebElement userSingleNotFoundLink

    @FindBy(css = "[data-id='unknown-single-not-found']")
    private WebElement singleResourceNotFoundLink

    @FindBy(css = "[data-key*='output-response']")
    private WebElement dataResponse

    def goToHomePage(){
        pageAction.navigateTo("https://reqres.in")
        log.info("Welcome to home page")
    }

    def refreshPage(){
        pageAction.refreshPage()
    }

    def clickOnUserSingleNotFoundLink(){
        pageAction.click(userSingleNotFoundLink)
    }

    def clickOnSingleResourceNotFoundLink(){
        pageAction.click(singleResourceNotFoundLink)
    }

    def getDataResponse(){
        return pageAction.getText(dataResponse)
    }

    def getRequestPath(){
        return pageAction.getText(requestPath)
    }

    def getResponseCode(){
        pageAction.waitForPageLoad()
        return pageAction.getText(responseCode)
    }

//    def getTagLineTitle(){
//        return pageAction.getText(tagLineTitle)
//    }

    def isKeySellExist(String keySell){
        def values = keySells.collect{element -> pageAction.getText(element).trim()}
        return values.any{value -> keySell.equalsIgnoreCase(value)}
    }
}
