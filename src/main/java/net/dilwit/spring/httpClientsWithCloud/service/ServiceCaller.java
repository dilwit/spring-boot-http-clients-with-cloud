package net.dilwit.spring.httpClientsWithCloud.service;

import net.dilwit.spring.httpClientsWithCloud.feign.external.google.client.SearchClient;
import net.dilwit.spring.httpClientsWithCloud.feign.internal.EurekaAwareFeignClient;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestClientLoadBalanced;
import net.dilwit.spring.httpClientsWithCloud.restTemplate.EurekaAwareRestTemplateClientNonLoadBalanced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCaller {

    private static String EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT = "Hello from EurekaAwareClient!";

    @Autowired
    EurekaAwareFeignClient eurekaAwareFeignClient;

    @Autowired
    SearchClient searchClient;

    @Autowired
    EurekaAwareRestTemplateClientNonLoadBalanced eurekaAwareRestTemplateClientNonLoadBalanced;

    @Autowired
    EurekaAwareRestClientLoadBalanced eurekaAwareRestClientLoadBalanced;

    public void invokeServices() {

        invokeRestTemplateService();
        invokeFeignBasedService();

    }

    private void invokeRestTemplateService() {

        if(!eurekaAwareRestTemplateClientNonLoadBalanced.greeting().equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking client service via rest template - non-load balanced");

        if(!eurekaAwareRestClientLoadBalanced.greeting().equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking client service via rest template - load balanced");
    }

    private void invokeFeignBasedService() {

        String eurekaAwareResult = eurekaAwareFeignClient.greeting();
        if(!eurekaAwareResult.equalsIgnoreCase(EXPECTED_GREETING_FROM_EUREKA_AWARE_CLIENT))
            throw new RuntimeException("Error invoking client service via fiegn client");
    }

}
