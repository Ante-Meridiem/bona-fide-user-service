package com.ante.meridiem.infra.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import com.ante.meridiem.api.impl.JwtUserDetailsService;
import com.ante.meridiem.api.impl.UserServiceImpl;
import com.ante.meridiem.spi.UserRepository;

@Configuration
public class DomainConfiguration {

    @Bean
    public UserServiceImpl userService(UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtUserDetailsService jwtUserDetailsService){
        return new UserServiceImpl(userRepository,authenticationManager,jwtUserDetailsService);
    }

    @Bean
    public JwtUserDetailsService jwtUserDetailsService(UserRepository userRepository){
        return new JwtUserDetailsService(userRepository);
    }
}
