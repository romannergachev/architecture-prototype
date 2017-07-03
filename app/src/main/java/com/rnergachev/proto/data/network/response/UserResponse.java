package com.rnergachev.proto.data.network.response;

/**
 * Created by rnergachev on 30/06/2017.
 */

public class UserResponse {
    private int id;
    private String name;
    private String username;
    private String email;

    public UserResponse() {

    }

    public UserResponse(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
