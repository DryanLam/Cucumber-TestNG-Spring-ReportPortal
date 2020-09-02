package com.sample.dl.bdd.utils.ws

import com.sample.dl.bdd.utils.exceptions.TestException
import org.springframework.web.client.RestTemplate

class BaseGateway {

    protected RestTemplate restTemplate;
    protected final String baseUri;

    BaseGateway(RestTemplate restTemplate, String baseUri) {
        this.restTemplate = restTemplate;
        this.baseUri = baseUri;
    }

    protected URI buildUri(String relativeUri) {
        try {
            return new URI(buildURIAsString(relativeUri));
        } catch (URISyntaxException e) {
            throw new TestException("*** ERROR: Could not build URI", e);
        }
    }

    protected String buildURIAsString(String relativeUri) {
        return baseUri + relativeUri;
    }
}
