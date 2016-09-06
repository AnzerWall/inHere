package com.inHere.entity;

public class CryptonymUsed {
    private Integer id;

    private Integer extType;

    private Integer recordId;

    private String userId;

    private Integer cryptonymId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExtType() {
        return extType;
    }

    public void setExtType(Integer extType) {
        this.extType = extType;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCryptonymId() {
        return cryptonymId;
    }

    public void setCryptonymId(Integer cryptonymId) {
        this.cryptonymId = cryptonymId;
    }
}