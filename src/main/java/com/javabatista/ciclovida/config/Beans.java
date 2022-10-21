package com.javabatista.ciclovida.config;

import com.javabatista.ciclovida.util.MockDbData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    @Bean
    public MockDbData mockDbData() {
        return new MockDbData();
    }
}
