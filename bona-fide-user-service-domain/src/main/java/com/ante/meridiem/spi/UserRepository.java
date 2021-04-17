package com.ante.meridiem.spi;

import com.ante.meridiem.model.DomainUser;

public interface UserRepository {
    public String saveUser(DomainUser domainUser);
    public DomainUser findUser(String username);
}
