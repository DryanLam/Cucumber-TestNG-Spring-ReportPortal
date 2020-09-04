package com.sample.dl.bdd.utils.ws

import io.restassured.response.Response
import org.springframework.stereotype.Service

@Service
class WSResponse {
    Response response

    def setResponse(Response response) {
        this.response = response
    }

    def getResponse() {
        return response
    }

    def getStatusCode() {
        return response.getStatusCode()
    }

    def getBody() {
        return response.getBody()
    }

    def getBodyAsString(def pretty = true) {
        if (pretty) {
            return response.getBody().prettyPrint()
        }
        return response.getBody().asString()
    }

}
