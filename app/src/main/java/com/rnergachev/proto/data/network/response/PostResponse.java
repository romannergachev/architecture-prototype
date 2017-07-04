package com.rnergachev.proto.data.network.response;

/**
 * Retrofit Post response
 *
 * Created by rnergachev on 30/06/2017.
 */

public class PostResponse {
    private int userId;
    private int id;
    private String title;
    private String body;

    public PostResponse() {

    }

    public PostResponse(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
