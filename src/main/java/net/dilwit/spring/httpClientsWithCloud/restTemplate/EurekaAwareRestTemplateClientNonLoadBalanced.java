package net.dilwit.spring.httpClientsWithCloud.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Available services identified via DiscoveryClient and
 * always pick first available service to make the call.
 */
@Component
public class EurekaAwareRestTemplateClientNonLoadBalanced extends AbstractEurekaAwareRestClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String greeting() {
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-AWARE-CLIENT");

        if(instances.size() <= 0)
            throw new RuntimeException("Not able to fetch registry from eureka server. " +
                    "Either no services registered or eureka.client.fetchRegistry settings on in bootstrap.yml");

        ServiceInstance serviceInstance = instances.get(0);
        String result = restTemplate.exchange("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/greeting", HttpMethod.GET, new HttpEntity<String>(createHeaders("eureka", "123456789")), String.class).getBody();

        return result;
    }
}
