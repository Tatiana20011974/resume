package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Slf4j
@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.example.demo.redisRepository")
@EnableJpaRepositories(basePackages = "com.example.demo.SpringDataRepository")
public class Demo2Application {

    public static void main(String[] args) {

        SpringApplication.run(Demo2Application.class, args);

        log.warn("--------APPLICATION STARTED");
        log.info("--------APPLICATION STARTED");
        log.debug("--------APPLICATION STARTED");
    }

}
