package cl.duoc.msequipamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsEquipamientoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsEquipamientoApplication.class, args);
    }
}