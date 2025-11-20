package org.csu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "org.csu.client")  // ⭐ 关键
public class PetTradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetTradeApplication.class, args);
    }
}
