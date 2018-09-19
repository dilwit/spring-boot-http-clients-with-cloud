package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAware;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("EUREKA-AWARE-CLIENT")
public interface EurekaAwareFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/greeting")
    String greeting();

}
