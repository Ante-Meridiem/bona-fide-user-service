package com.ante.meridiem.api.impl;

import static java.util.Collections.emptyList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ante.meridiem.model.DomainUser;
import com.ante.meridiem.spi.UserRepository;

public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DomainUser domainUser = userRepository.findUser(username);
        if(domainUser.getUsername().equals(username)){
            return new User(domainUser.getUsername(), domainUser.getPassword(), emptyList());
        }
        else
            throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
