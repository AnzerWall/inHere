package com.inHere.entity;

public class NotNotice {
    private Integer id;

    private Integer notificationId;

    private String userId;

    private Byte hasAccept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Byte getHasAccept() {
        return hasAccept;
    }

    public void setHasAccept(Byte hasAccept) {
        this.hasAccept = hasAccept;
    }
}