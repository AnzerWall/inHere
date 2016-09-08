package com.inHere.entity;

public class TargetListUser {
    private Integer id;

    private Integer targetListId;

    private String userId;

    private Double process;

    private Integer hasGiveUp;

    private Integer isPass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTargetListId() {
        return targetListId;
    }

    public void setTargetListId(Integer targetListId) {
        this.targetListId = targetListId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getProcess() {
        return process;
    }

    public void setProcess(Double process) {
        this.process = process;
    }

    public Integer getHasGiveUp() {
        return hasGiveUp;
    }

    public void setHasGiveUp(Integer hasGiveUp) {
        this.hasGiveUp = hasGiveUp;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }
}