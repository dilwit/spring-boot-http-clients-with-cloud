package net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAwareAndHystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EurekaAwareFeignClientWithHystrixFallBack implements EurekaAwareFeignClientWithHystrix {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaAwareFeignClientWithHystrixFallBack.class);

    private Throwable cause;

    public EurekaAwareFeignClientWithHystrixFallBack(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String greeting(String from) {
        LOGGER.info(cause.toString());
        return "Fallen back to empty greeting... as no greeting for " + from;
    }

}
