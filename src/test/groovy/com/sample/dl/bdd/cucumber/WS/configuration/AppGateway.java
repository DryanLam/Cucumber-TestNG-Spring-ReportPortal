package com.sample.dl.bdd.cucumber.WS.configuration;

import com.sample.dl.bdd.utils.ws.BaseGateway;
import com.sample.dl.bdd.utils.ws.WSResponse;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Component
class AppGateway extends BaseGateway {

    @Autowired
    WSConfiguration wsConfig;

    @Value("${endpoint.ws.authentication}")
    private String auEndpoint;

    @Autowired
    WSResponse res;

    @Autowired
    public AppGateway(RestTemplate restTemplate, @Value("${ws.app.url}") String baseUri) {
        super(restTemplate, baseUri);
    }

    private CookieFilter filter = new CookieFilter();
    private Map<String,Object> header = new HashMap<String,Object>();

    public void setHeader(Map header){
        this.header = header;
    }

    public Response doAuthentication(String userName, String passWord, Object bodyObj){
        return given()
                .filter(filter)
                .headers(header)
                .body(bodyObj)
                .post(buildUri(auEndpoint))
                .andReturn();
    }

    public void doPostRequest(String uriPath, Object bodyObj) {
        Response response = given()
                             .filter(filter)
                             .contentType(ContentType.JSON)
                             .headers(header)
                             .body(bodyObj)
                             .post(buildUri(uriPath))
                             .thenReturn();
        res.setResponse(response);
    }

    public void doGetRequest(String uriPath) {
        Response response =  given()
                                .filter(filter)
                                .headers(header)
                                .contentType(ContentType.JSON)
                                .get(buildUri(uriPath))
                                .thenReturn();
        res.setResponse(response);
    }
}
