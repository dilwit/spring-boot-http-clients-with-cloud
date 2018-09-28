package net.dilwit.spring.httpClientsWithCloud;

import net.dilwit.spring.httpClientsWithCloud.service.ServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class HttpClientsWithCloudApplication {

	@Autowired
	ServiceCaller serviceCaller;

	private static String SCENARIO_0_STRING = "scenario-0";
	private static String SCENARIO_1_STRING = "scenario-1";
	private static String SCENARIO_2_STRING = "scenario-2";
	private static String SCENARIO_3_STRING = "scenario-3";
	private static String SCENARIO_4_STRING = "scenario-4";

	private static String CURRENT_SCENARIO = SCENARIO_1_STRING;

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, CURRENT_SCENARIO);
		SpringApplication.run(HttpClientsWithCloudApplication.class, args);
	}

	@PostConstruct
	public void init() {

		if(CURRENT_SCENARIO.equals(SCENARIO_0_STRING))
			serviceCaller.invokePlainServiceTests();

		if(CURRENT_SCENARIO.equals(SCENARIO_1_STRING))
			serviceCaller.invokeLoadBalancedFiegnClient_Scenario_1();

		if(CURRENT_SCENARIO.equals(SCENARIO_2_STRING))
			serviceCaller.invokeLoadBalancedFiegnClient_Scenario_2();

		if(CURRENT_SCENARIO.equals(SCENARIO_3_STRING))
			serviceCaller.invokeLoadBalancedFiegnClientWithHystrix_Scenario_3();

		if(CURRENT_SCENARIO.equals(SCENARIO_4_STRING))
			serviceCaller.invokeLoadBalancedFiegnClientWithHystrix_Scenario_4();

	}
}
