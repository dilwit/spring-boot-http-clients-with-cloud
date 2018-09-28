package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

public class AbstractEurekaAwareRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEurekaAwareRestClient.class);

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

    protected void printServiceInstance(ServiceInstance i) {
        LOGGER.info("Instance: Host - " + i.getHost() + " Port - " + i.getPort());
    }
}
