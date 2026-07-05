package cl.duoc.mscanchas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCanchasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsCanchasApplication.class, args);
    }
}