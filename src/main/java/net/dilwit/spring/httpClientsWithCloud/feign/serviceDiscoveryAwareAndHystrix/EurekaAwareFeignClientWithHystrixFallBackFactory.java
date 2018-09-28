package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAwareAndHystrix;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class EurekaAwareFeignClientWithHystrixFallBackFactory implements FallbackFactory<EurekaAwareFeignClientWithHystrix> {

    @Override
    public EurekaAwareFeignClientWithHystrix create(Throwable throwable) {
        return new EurekaAwareFeignClientWithHystrixFallBack(throwable);
    }

}
