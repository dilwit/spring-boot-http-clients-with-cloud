package net.dilwit.spring.httpClientsWithCloud.feign.internal;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "EUREKA-AWARE-CLIENT", fallback = EurekaAwareFeignClientFallBack.class)
public interface EurekaAwareFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/greeting")
    String greeting();

}
