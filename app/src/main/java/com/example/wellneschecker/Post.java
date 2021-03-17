package com.example.wellneschecker;

public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;


    public int getId() {
        return id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getuserId() {
        return userId;
    }
    public void setuserId(int userId) {
        this.userId = userId;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Post = {id = %d and title = %s}", id, title);
    }
}
