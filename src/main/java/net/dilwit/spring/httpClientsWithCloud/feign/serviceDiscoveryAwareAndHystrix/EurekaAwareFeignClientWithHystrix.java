package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAwareAndHystrix;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "EUREKA-AWARE-CLIENT", fallback = EurekaAwareFeignClientWithHystrixFallBack.class)
public interface EurekaAwareFeignClientWithHystrix {

    @RequestMapping(method = RequestMethod.GET, path = "/greeting")
    String greeting();

}
