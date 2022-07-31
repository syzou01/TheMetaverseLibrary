package cs.hku.metalib.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "cs.hku.metalib.admin.feign")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "admin")
public class MetalibAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalibAdminApplication.class, args);
	}

}
