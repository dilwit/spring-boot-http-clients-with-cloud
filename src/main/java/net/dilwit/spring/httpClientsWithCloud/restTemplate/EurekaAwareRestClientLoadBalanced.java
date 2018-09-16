package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class EurekaAwareRestClientLoadBalanced extends AbstractEurekaAwareRestClient {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String greeting() {

        ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-AWARE-CLIENT");
        String result = restTemplate.exchange(serviceInstance.getUri() + "/greeting", HttpMethod.GET, null, String.class).getBody();
        return result;
    }
}
