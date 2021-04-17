package com.ante.meridiem.api.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import com.ante.meridiem.api.UserService;
import com.ante.meridiem.model.AuthenticationDomainRequest;
import com.ante.meridiem.model.DomainUser;
import com.ante.meridiem.spi.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;

    public UserServiceImpl(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtUserDetailsService jwtUserDetailsService
    ){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public String saveUser(DomainUser domainUser) {
        return userRepository.saveUser(domainUser);
    }

    @Override
    public UserDetails authenticateUser(AuthenticationDomainRequest authenticationDomainRequest) throws Exception {
        authenticate(authenticationDomainRequest.getUsername(),authenticationDomainRequest.getPassword());
        return jwtUserDetailsService
                .loadUserByUsername(authenticationDomainRequest.getUsername());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
