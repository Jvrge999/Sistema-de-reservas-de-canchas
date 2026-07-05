package cl.duoc.msfairplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsFairplayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFairplayApplication.class, args);
    }
}