package com.inHere.entity;

public class TargetUser {
    private Integer id;

    private Integer targetId;

    private String userId;

    private String extData;

    private Byte hasFinish;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getExtData() {
        return extData;
    }

    public void setExtData(String extData) {
        this.extData = extData == null ? null : extData.trim();
    }

    public Byte getHasFinish() {
        return hasFinish;
    }

    public void setHasFinish(Byte hasFinish) {
        this.hasFinish = hasFinish;
    }
}