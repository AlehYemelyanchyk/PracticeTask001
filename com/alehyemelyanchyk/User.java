package com.alehyemelyanchyk;

import java.util.UUID;

public class User {

    private String login;
    private String password;
    private String email;
    private String id;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        id = UUID.randomUUID().toString();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }


    public static User createUser(String login, String password, String email) {
        return new User(login, (String.valueOf(password.hashCode())), email);
    }

    public static User createUserNoHash(String login, String password, String email) {
        return new User(login, password, email);
    }

    @Override
    public String toString() {
        return login + ";" + password + ";" + email + ";" + id;
    }
}
