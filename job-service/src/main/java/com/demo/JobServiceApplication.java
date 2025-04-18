package com.demo;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class JobServiceApplication {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceApplication.class);

    public static void main(String[] args) {
        logger.info("✅ Spring Boot Cache is ENABLED");
        SpringApplication.run(JobServiceApplication.class, args);
    }


}
