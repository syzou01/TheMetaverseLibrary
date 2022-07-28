package cs.hku.metalib.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "user")
public class MetalibUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalibUserApplication.class, args);
	}

}
