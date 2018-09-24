package net.dilwit.spring.httpClientsWithCloud.service;

import net.dilwit.spring.httpClientsWithCloud.feign.external.CurrencyFeignClient;
import net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAware.EurekaAwareFeignClient;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestTemplateClientNonLoadBalanced;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestTemplateClientLoadBalanced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCaller {

    private static final Logger LOGGER = LoggerFactory.getLogger(EurekaAwareRestTemplateClientLoadBalanced.class);

    private static String EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT = "Hello from EurekaAwareClient!";

    @Autowired
    EurekaAwareFeignClient eurekaAwareFeignClient;

    @Autowired
    CurrencyFeignClient currencyFeignClient;

    @Autowired
    EurekaAwareRestTemplateClientNonLoadBalanced eurekaAwareRestTemplateClientNonLoadBalanced;

    @Autowired
    EurekaAwareRestTemplateClientLoadBalanced eurekaAwareRestTemplateClientLoadBalanced;

    public void invokePlainServiceTests() {
        invokeRestTemplateService();
        invokeFeignBasedService();
    }

    /**
     * Pre-requisites:
     * - two instances of eurekaAware client should run
     * - one of them should run with execution delay which is greater than ribbon readTimeOut
     *
     * Scenario explained:
     * - each iteration will have 50/50 chance in hitting highly responsive service and debug-blocked service
     * - iterations which hit the service with execution delay will not retry with other available service instances
     */
    public void invokeLoadBalancedFiegnClientScenario_1() {

        for(int i = 0; i < 10; i++) {
            String greeting = eurekaAwareFeignClient.greeting();
            if (greeting.equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
                LOGGER.info("Scenario 1 - Iteration: " + i + " Service returned: " + greeting);
        }
    }

    /**
     * Pre-requisites:
     * - two instances of eurekaAware client should run
     * - one of them should run with execution delay which is greater than ribbon readTimeOut
     *
     * Scenario explained:
     * - any iteration which hit the service with execution delay will time-out and try next available service
     * - hence, each iteration will hit highly responsive service
     */
    public void invokeLoadBalancedFiegnClientScenario_2() {

        for(int i = 0; i < 10; i++) {
            String greeting = eurekaAwareFeignClient.greeting();
            if (greeting.equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
                LOGGER.info("Scenario 2 - Iteration: " + i + " Service returned: " + greeting);
        }
    }

    private void invokeRestTemplateService() {

        if(!eurekaAwareRestTemplateClientNonLoadBalanced.greeting().equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking client service via rest template - non-load balanced");

        if(!eurekaAwareRestTemplateClientLoadBalanced.greeting().equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking client service via rest template - load balanced");
    }

    private void invokeFeignBasedService() {

        if(!currencyFeignClient.getCurrencies().contains("currencyName"))
            throw new RuntimeException("Error invoking external service via feign client");

        if(!eurekaAwareFeignClient.greeting().equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking eureka aware service via feign client");
    }

}
