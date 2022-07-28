package cs.hku.metalib.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "cs.hku.metalib.message.Feign")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "message")
public class MetalibMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalibMessageApplication.class, args);
	}

}
