package net.dilwit.spring.httpClientsWithCloud.service;

import net.dilwit.spring.httpClientsWithCloud.feign.external.CurrencyFeignClient;
import net.dilwit.spring.httpClientsWithCloud.feign.serviceDiscoveryAware.EurekaAwareFeignClient;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestTemplateClientNonLoadBalanced;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestTemplateClientLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCaller {

    private static String EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT = "Hello from EurekaAwareClient!";

    @Autowired
    EurekaAwareFeignClient eurekaAwareFeignClient;

    @Autowired
    CurrencyFeignClient currencyFeignClient;

    @Autowired
    EurekaAwareRestTemplateClientNonLoadBalanced eurekaAwareRestTemplateClientNonLoadBalanced;

    @Autowired
    EurekaAwareRestTemplateClientLoadBalanced eurekaAwareRestTemplateClientLoadBalanced;

    public void invokeServices() {

        invokeRestTemplateService();
        invokeFeignBasedService();

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
