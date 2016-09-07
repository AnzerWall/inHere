package com.inHere.entity;

public class TargetListUser {
    private Integer id;

    private Integer targetListId;

    private String userId;

    private Double process;

    private Byte hasGiveUp;

    private Byte isPass;

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

    public Byte getHasGiveUp() {
        return hasGiveUp;
    }

    public void setHasGiveUp(Byte hasGiveUp) {
        this.hasGiveUp = hasGiveUp;
    }

    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }
}