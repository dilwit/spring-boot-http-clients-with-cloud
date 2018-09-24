package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class EurekaAwareRestTemplateClientLoadBalanced extends AbstractEurekaAwareRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaAwareRestTemplateClientLoadBalanced.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String greeting() {

        // Let load balancer to pick the service
        ServiceInstance serviceInstance = loadBalancerClient.choose(EUREKA_AWARE_CLIENT_SERVICE_ID);
        LOGGER.info("Service instance picked up by load balancer: ");
        printServiceInstance(serviceInstance);
        String result = restTemplate.exchange(serviceInstance.getUri() + "/greeting", HttpMethod.GET, null, String.class).getBody();

        LOGGER.info("Service returns: " + result);
        return result;
    }
}
