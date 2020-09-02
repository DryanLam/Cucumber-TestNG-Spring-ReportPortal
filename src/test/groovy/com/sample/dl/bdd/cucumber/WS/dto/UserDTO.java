package com.sample.dl.bdd.cucumber.WS.dto;

import java.util.Objects;

public class UserDTO {
    private String title;
    private String body;
    private int userId;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) ob;
        return Objects.equals(userDTO.getTitle(), title)
                && Objects.equals(userDTO.getBody(), body)
                && Objects.equals(userDTO.getUserId(), userId)
                && Objects.equals(userDTO.getId(), id);
    }

    @Override
    public int hashCode() {
        return (title == null ? 0 : title.hashCode())
                + (body == null ? 0 : body.hashCode());
    }
}
