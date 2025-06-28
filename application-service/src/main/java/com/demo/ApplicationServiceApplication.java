package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
//@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
//@EnableJpaRepositories(basePackages = "com.demo.repository.jpa")
//@EnableElasticsearchRepositories(basePackages = "com.demo.repository.elasticsearch")
public class ApplicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationServiceApplication.class, args);
    }

}
