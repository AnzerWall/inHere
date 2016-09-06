package com.inHere.entity;

import java.util.Date;

public class Target {
    private Integer id;

    private String title;

    private String text;

    private Integer extType;

    private String extData;

    private Byte hasFinish;

    private Integer targetListId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getExtType() {
        return extType;
    }

    public void setExtType(Integer extType) {
        this.extType = extType;
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

    public Integer getTargetListId() {
        return targetListId;
    }

    public void setTargetListId(Integer targetListId) {
        this.targetListId = targetListId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}