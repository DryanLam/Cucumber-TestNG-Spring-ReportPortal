package com.sample.dl.bdd.cucumber.WS.steps

import com.sample.dl.bdd.cucumber.WS.configuration.AppGateway
import com.sample.dl.bdd.cucumber.WS.dto.UserDTO
import com.sample.dl.bdd.utils.asserts.Assert
import com.sample.dl.bdd.utils.common.Parser
import com.sample.dl.bdd.utils.ws.WSResponse
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.springframework.beans.factory.annotation.Autowired

class FakeDataWSSteps {

    @Autowired
    AppGateway gateway

    @Autowired
    WSResponse response

    @Autowired
    Parser parser

    @Given(/^We use default header$/)
    def we_use_default_header(){
        def defaultHeader = new HashMap<String,Object>()
        gateway.setHeader(defaultHeader)
    }

    @When(/^We send a POST request to '(.+?)' endpoint with body:$/)
    def we_send_a_POST_request_to_endpoint_with_body(String endPoint, String dataDody) {
        gateway.doPostRequest(endPoint, dataDody)
    }

    @When(/^We send a GET request to '(.+?)' endpoint$/)
    def we_send_a_GET_request_to_endpoint(String endPoint) {
        gateway.doGetRequest(endPoint)
    }

    @Then(/^We got the Response with status code '(.+?)'$/)
    def we_got_the_Response_with_status_code(Integer httpCode) {
        def statusCode = response.getStatusCode()
        Assert.assertEquals(httpCode, statusCode)
    }

    @Then(/^We got the Response with body:$/)
    def we_got_the_Response_with_body(String jsonBody) {
        UserDTO expected = parser.parseFromJson(jsonBody, UserDTO.class);
        UserDTO actual = parser.parseFromJson(response.getBodyAsString(), UserDTO.class);
        Assert.assertEquals(expected, actual)
    }
}
