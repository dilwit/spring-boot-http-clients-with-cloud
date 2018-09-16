package net.dilwit.spring.httpClientsWithCloud.feign.internal;

import org.springframework.stereotype.Component;

@Component
public class EurekaAwareFeignClientFallBack implements EurekaAwareFeignClient {

    @Override
    public String greeting() {
        throw new RuntimeException("Eureka Aware Client is not reachable");
    }
}
