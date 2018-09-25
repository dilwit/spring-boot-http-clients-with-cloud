package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAwareAndHystrix;

import org.springframework.stereotype.Component;

@Component
public class EurekaAwareFeignClientWithHystrixFallBack implements EurekaAwareFeignClientWithHystrix {

    @Override
    public String greeting() {
        return "Fallen back to empty greeting...";
    }
}
