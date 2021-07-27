package com.jscisco.lom.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.persistence.GameVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.jscisco.lom")
public class ApplicationConfiguration {

    @Value("${game.version}")
    String gameVersion;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public GameVersion gameVersion() {
        return GameVersion.of(gameVersion);
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
