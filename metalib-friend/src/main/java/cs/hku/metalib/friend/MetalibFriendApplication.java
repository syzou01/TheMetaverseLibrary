package cs.hku.metalib.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "cs.hku.metalib.friend.Feign")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "friend")
public class MetalibFriendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetalibFriendApplication.class, args);
	}

}
