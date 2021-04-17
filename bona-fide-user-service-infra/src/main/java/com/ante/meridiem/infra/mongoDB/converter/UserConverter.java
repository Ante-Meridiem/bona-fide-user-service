package com.ante.meridiem.infra.mongoDB.converter;

import com.ante.meridiem.infra.application.model.AuthenticationRequest;
import com.ante.meridiem.infra.application.model.RestUser;
import com.ante.meridiem.infra.mongoDB.model.MongoUser;
import com.ante.meridiem.model.AuthenticationDomainRequest;
import com.ante.meridiem.model.DomainUser;

public class UserConverter {

    public MongoUser toMongoUser(DomainUser domainUser){
        return new MongoUser(domainUser.getUsername(),domainUser.getPassword());
    }

    public DomainUser toDomainUser(RestUser restUser){
        return new DomainUser(restUser.getUsername(),restUser.getPassword());
    }

    public DomainUser toDomainUser(MongoUser mongoUser){
        return new DomainUser(mongoUser.getUsername(),mongoUser.getPassword());
    }

    public AuthenticationDomainRequest toDomainAuthenticationRequest(AuthenticationRequest authenticationRequest) {
        return new AuthenticationDomainRequest(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());
    }

}
