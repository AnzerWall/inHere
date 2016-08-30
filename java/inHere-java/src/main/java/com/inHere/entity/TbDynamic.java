package com.inHere.entity;

public class TbDynamic {
    private Integer dyId;

    private String beginTime;

    private String dyPoint;

    private String userIdMaster;

    public Integer getDyId() {
        return dyId;
    }

    public void setDyId(Integer dyId) {
        this.dyId = dyId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getDyPoint() {
        return dyPoint;
    }

    public void setDyPoint(String dyPoint) {
        this.dyPoint = dyPoint == null ? null : dyPoint.trim();
    }

    public String getUserIdMaster() {
        return userIdMaster;
    }

    public void setUserIdMaster(String userIdMaster) {
        this.userIdMaster = userIdMaster == null ? null : userIdMaster.trim();
    }
}