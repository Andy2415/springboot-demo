package com.cz2415;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class FileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("1KB"); //KB,MB
        return factory.createMultipartConfig();
    }
}
