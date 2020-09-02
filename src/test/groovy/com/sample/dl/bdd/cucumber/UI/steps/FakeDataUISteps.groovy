package com.sample.dl.bdd.cucumber.UI.steps

import com.sample.dl.bdd.cucumber.UI.pages.HomePage
import com.sample.dl.bdd.utils.asserts.Assert
import groovy.util.logging.Slf4j
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired

@Slf4j
class FakeDataUISteps {

    @Autowired
    HomePage homePage


    @Given(/^I navigate to home page/)
    def i_navigate_to_home_page() {
        homePage.goToHomePage()
        log.info("I NAVIGATED TO HOME PAGE")

    }

    @When(/^I refresh the homepage$/)
    def i_refresh_the_home_page() {
        homePage.refreshPage()
    }

    @Then(/^The '(.+)' should be displayed correctly$/)
    def the_key_sells_should_be_displayed(def keySell) {
        def isExisted = homePage.isKeySellExist(keySell)
        Assert.assertTrue(isExisted)

    }

    @Given(/^I click on '(.+)' of '(.+)' at Give it a try$/)
    def i_click_on_method_of_request_at_give_a_try(String methodName, String requestName) {
        if ("GET".equalsIgnoreCase(methodName)) {
            if ("SINGLE USER NOT FOUND".equalsIgnoreCase(requestName)) {
                homePage.clickOnUserSingleNotFoundLink()
            } else {
                homePage.clickOnSingleResourceNotFoundLink()
            }
        }
    }


    @Then(/^The request '(.+)' is display on the left column$/)
    def the_request_api_users_is_display_on_the_left_column(String uri) {
        def uriOnLeftColumn = homePage.getRequestPath()
        Assert.assertEquals(uri, uriOnLeftColumn)
    }


    @Then(/^The response code '(.+)'is displayed on the right column$/)
    def the_is_displayed_on_the_right_column(Integer httpCode) {
        def responseCodeOnRightColumn = homePage.getResponseCode()
        Assert.assertEquals(httpCode.toString(), responseCodeOnRightColumn)
    }

    @Then(/^The response data'(.+)' is displayed under right column$/)
    def the_is_displayed_under_right_column(String data) {
        def responseDataUnderRightColumn = homePage.getDataResponse()
        Assert.assertEquals(data, responseDataUnderRightColumn)
    }
}
