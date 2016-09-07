package com.inHere.entity;

public class AskReplyUser {
    private Integer id;

    private String userId;

    private Integer askReplyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getAskReplyId() {
        return askReplyId;
    }

    public void setAskReplyId(Integer askReplyId) {
        this.askReplyId = askReplyId;
    }
}