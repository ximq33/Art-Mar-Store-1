package com.ArtMar_Store.Api.infrastructure;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan
public class AppConfig {

    @Bean
    FileStorageProperties fileStorageProperties(){
        return new FileStorageProperties();
    }
}
