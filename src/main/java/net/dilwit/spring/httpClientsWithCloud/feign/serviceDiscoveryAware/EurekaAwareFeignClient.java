package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAware;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("EUREKA-AWARE-CLIENT")
public interface EurekaAwareFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/greeting/{from}")
    String greeting(@PathVariable("from") String from);

}