package com.ante.meridiem.infra.mongoDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ante.meridiem.infra.mongoDB.converter.UserConverter;

@Configuration
public class ConverterConfiguration {

    @Bean
    public UserConverter userConverter(){
        return new UserConverter();
    }
}
