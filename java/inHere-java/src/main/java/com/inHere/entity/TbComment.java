package com.inHere.entity;

public class TbComment {
    private Integer commentId;

    private String tabName;

    private Integer recordId;

    private String commentContent;

    private String commentTime;

    private String userIdPush;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName == null ? null : tabName.trim();
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime == null ? null : commentTime.trim();
    }

    public String getUserIdPush() {
        return userIdPush;
    }

    public void setUserIdPush(String userIdPush) {
        this.userIdPush = userIdPush == null ? null : userIdPush.trim();
    }
}