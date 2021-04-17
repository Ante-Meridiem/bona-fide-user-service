package com.ante.meridiem.infra.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ante.meridiem.api.UserService;
import com.ante.meridiem.infra.application.model.AuthenticationRequest;
import com.ante.meridiem.infra.application.model.RestUser;
import com.ante.meridiem.infra.application.security.JwtResponse;
import com.ante.meridiem.infra.application.security.JwtTokenUtil;
import com.ante.meridiem.infra.mongoDB.converter.UserConverter;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class APIController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    private final UserConverter userConverter;

    private AuthenticationManager authenticationManager;

    public APIController(JwtTokenUtil jwtTokenUtil,UserService userService,UserConverter userConverter){
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PostMapping("/save")
    private String saveUser(@RequestBody RestUser restUser){
        return userService.saveUser(userConverter.toDomainUser(restUser));
    }

    @PostMapping("/authenticate")
    private ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        UserDetails userDetails = userService
                .authenticateUser(userConverter.toDomainAuthenticationRequest(authenticationRequest));
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

}
