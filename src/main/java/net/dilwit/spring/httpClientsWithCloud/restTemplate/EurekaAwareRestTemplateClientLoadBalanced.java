package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class EurekaAwareRestTemplateClientLoadBalanced extends AbstractEurekaAwareRestClient {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String greeting() {

        // Let load balancer to pick the service
        ServiceInstance serviceInstance = loadBalancerClient.choose(EUREKA_AWARE_CLIENT_SERVICE_ID);
        String result = restTemplate.exchange(serviceInstance.getUri() + "/greeting", HttpMethod.GET, null, String.class).getBody();

        return result;
    }
}
