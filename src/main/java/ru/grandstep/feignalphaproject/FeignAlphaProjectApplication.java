package ru.grandstep.feignalphaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignAlphaProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignAlphaProjectApplication.class, args);
    }

}
