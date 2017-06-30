package com.rnergachev.proto.data;

/**
 * Created by rnergachev on 29/06/2017.
 */

public class PostInfo {
    private int id;
    private String title;
    private String imageUrl;

    public PostInfo() {

    }

    public PostInfo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
