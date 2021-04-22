package com.ante.meridiem.infra.mongoDB.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.ante.meridiem.infra.mongoDB.converter.UserConverter;
import com.ante.meridiem.infra.mongoDB.model.MongoUser;
import com.ante.meridiem.model.DomainUser;
import com.ante.meridiem.spi.UserRepository;

public  UserRepositoryImpl implements UserRepository {

    private final MongoOperations operations;

    private final  userConverter

    public UserRepositoryImpl(MongoOperations operations,UserConverter userConverter){
        this.operations = operations;
        this.userConverter = userConverter;
    }

    @Override
    public String saveUser(DomainUser domainUser) {
        operations.save(userConverter.toMongoUser(domainUser));
        return "User saved";
    }

    @Override
    public DomainUser findUser(String username) {
        Query userQuery = Query.query(Criteria.where("username").is(username));
        MongoUser mongoUser = operations.findOne(userQuery, MongoUser.class, "Users");
        return userConverter.toDomainUser(mongoUser);
    }
}
