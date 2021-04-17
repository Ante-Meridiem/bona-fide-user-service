package com.ante.meridiem.api;

import org.springframework.security.core.userdetails.UserDetails;

import com.ante.meridiem.model.AuthenticationDomainRequest;
import com.ante.meridiem.model.DomainUser;


public interface UserService {
    public String saveUser(DomainUser domainUser);
    UserDetails authenticateUser(AuthenticationDomainRequest authenticationDomainRequest) throws Exception;
}
