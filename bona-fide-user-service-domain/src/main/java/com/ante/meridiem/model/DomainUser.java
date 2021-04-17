package com.ante.meridiem.model;

import java.util.Objects;

public class DomainUser {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomainUser)) {
            return false;
        }
        DomainUser that = (DomainUser) o;
        return getUsername().equals(that.getUsername()) && getPassword().equals(that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DomainUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
