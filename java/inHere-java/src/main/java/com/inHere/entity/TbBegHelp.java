package com.inHere.entity;

public class TbBegHelp {
    private Integer helpId;

    private Integer lableId;

    private String labelData;

    private String wordDesc;

    private String picDesc;

    private Integer hasResolved;

    private String userIdBeg;

    private String praiseNum;

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public Integer getLableId() {
        return lableId;
    }

    public void setLableId(Integer lableId) {
        this.lableId = lableId;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData == null ? null : labelData.trim();
    }

    public String getWordDesc() {
        return wordDesc;
    }

    public void setWordDesc(String wordDesc) {
        this.wordDesc = wordDesc == null ? null : wordDesc.trim();
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc == null ? null : picDesc.trim();
    }

    public Integer getHasResolved() {
        return hasResolved;
    }

    public void setHasResolved(Integer hasResolved) {
        this.hasResolved = hasResolved;
    }

    public String getUserIdBeg() {
        return userIdBeg;
    }

    public void setUserIdBeg(String userIdBeg) {
        this.userIdBeg = userIdBeg == null ? null : userIdBeg.trim();
    }

    public String getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(String praiseNum) {
        this.praiseNum = praiseNum == null ? null : praiseNum.trim();
    }
}