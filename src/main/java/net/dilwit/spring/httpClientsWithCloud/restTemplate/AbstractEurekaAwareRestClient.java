package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class AbstractEurekaAwareRestClient {

    protected static final String EUREKA_AWARE_CLIENT_SERVICE_ID = "EUREKA-AWARE-CLIENT";

    @Autowired
    protected RestTemplate restTemplate;

    protected HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
