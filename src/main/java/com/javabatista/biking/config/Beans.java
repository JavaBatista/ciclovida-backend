package com.javabatista.biking.config;

import com.javabatista.biking.util.MockDbData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    public MockDbData mockDbData() {
        return new MockDbData();
    }
}
