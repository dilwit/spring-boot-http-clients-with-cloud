package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAwareAndHystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "EUREKA-AWARE-CLIENT", fallbackFactory = EurekaAwareFeignClientWithHystrixFallBackFactory.class)
public interface EurekaAwareFeignClientWithHystrix {

    @RequestMapping(method = RequestMethod.GET, value = "/greeting/{from}")
    String greeting(@PathVariable("from") String from);

}
