package com.inHere.entity;

import java.util.Date;

public class TipNotice {
    private Integer id;

    private Integer extType;

    private Integer recordId;

    private String acceptUser;

    private Byte action;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Byte hasRead;

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

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser == null ? null : acceptUser.trim();
    }

    public Byte getAction() {
        return action;
    }

    public void setAction(Byte action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Byte getHasRead() {
        return hasRead;
    }

    public void setHasRead(Byte hasRead) {
        this.hasRead = hasRead;
    }
}