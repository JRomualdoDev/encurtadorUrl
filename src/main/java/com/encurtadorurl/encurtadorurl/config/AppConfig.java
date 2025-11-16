package com.encurtadorurl.encurtadorurl.config;

import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("hashids.secret")
    private String secret;

    private final int SIZE_MIN = 5;

    @Bean
    public Hashids hashIds() {
        // The alphabet default is 62, you don't need config
        return new Hashids(secret, SIZE_MIN);
    }
}
