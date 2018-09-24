package net.dilwit.spring.httpClientsWithCloud;

import net.dilwit.spring.httpClientsWithCloud.service.ServiceCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class HttpClientsWithCloudApplication {

	@Autowired
	ServiceCaller serviceCaller;

	@Value("${scenario}")
	private int scenario;

	public static void main(String[] args) {
		SpringApplication.run(HttpClientsWithCloudApplication.class, args);
	}

	@PostConstruct
	public void init() {

		switch (scenario) {
			case 0:
				serviceCaller.invokePlainServiceTests();
				break;
			case 1:
				serviceCaller.invokeLoadBalancedFiegnClientScenario_1();
				break;
			case 2:
				serviceCaller.invokeLoadBalancedFiegnClientScenario_2();
				break;
		}
	}
}
