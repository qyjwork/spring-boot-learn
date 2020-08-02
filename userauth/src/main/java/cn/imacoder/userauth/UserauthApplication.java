package cn.imacoder.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserauthApplication.class, args);
    }

}
