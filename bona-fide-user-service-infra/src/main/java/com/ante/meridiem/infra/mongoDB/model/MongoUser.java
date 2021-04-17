package com.ante.meridiem.infra.mongoDB.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class MongoUser {
    private String username;

    public String getUsername() {
        return username;
    }

    public MongoUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
