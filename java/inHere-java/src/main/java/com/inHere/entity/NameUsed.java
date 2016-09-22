package com.inHere.entity;

public class NameUsed {
    private Integer id;

    private Integer moduleType;

    private Integer moduleId;

    private String userId;

    private Integer nameId;

    public NameUsed() {
    }
    public NameUsed(Integer moduleType, Integer moduleId, String userId) {
        this.moduleType = moduleType;
        this.moduleId = moduleId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleType() {
        return moduleType;
    }

    public void setModuleType(Integer moduleType) {
        this.moduleType = moduleType;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getNameId() {
        return nameId;
    }

    public void setNameId(Integer nameId) {
        this.nameId = nameId;
    }
}