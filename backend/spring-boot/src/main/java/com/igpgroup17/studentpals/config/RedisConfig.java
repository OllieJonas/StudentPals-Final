package com.igpgroup17.studentpals.config;

import io.lettuce.core.RedisURI;
import io.lettuce.core.resource.ClientResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisURI redis() {
        return RedisURI.Builder
                .redis("chat-service-001.kcs6jo.0001.euw1.cache.amazonaws.com", 6379)
                .withDatabase(1)
                .build();
    }

    @Bean
    public ClientResources clientResources() {
        return ClientResources.create();
    }
}
