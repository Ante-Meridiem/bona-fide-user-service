package com.ante.meridiem.infra.mongoDB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.ante.meridiem.infra.mongoDB.converter.UserConverter;
import com.ante.meridiem.infra.mongoDB.repository.UserRepositoryImpl;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoClientConfiguration {

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://13.235.2.41:27017/BonaFide");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public UserRepositoryImpl userRepository(MongoOperations operations, UserConverter userConverter) {
        return new UserRepositoryImpl(operations, userConverter);
    }

    @Override
    protected String getDatabaseName() {
        return "BonaFide";
    }
}
