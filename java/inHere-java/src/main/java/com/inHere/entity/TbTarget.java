package com.inHere.entity;

public class TbTarget {
    private Integer tarId;

    private String tarName;

    private String tarDesc;

    private Integer labelId;

    private String labelData;

    private Boolean hasFinish;

    private Integer barId;

    public Integer getTarId() {
        return tarId;
    }

    public void setTarId(Integer tarId) {
        this.tarId = tarId;
    }

    public String getTarName() {
        return tarName;
    }

    public void setTarName(String tarName) {
        this.tarName = tarName == null ? null : tarName.trim();
    }

    public String getTarDesc() {
        return tarDesc;
    }

    public void setTarDesc(String tarDesc) {
        this.tarDesc = tarDesc == null ? null : tarDesc.trim();
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData = labelData == null ? null : labelData.trim();
    }

    public Boolean getHasFinish() {
        return hasFinish;
    }

    public void setHasFinish(Boolean hasFinish) {
        this.hasFinish = hasFinish;
    }

    public Integer getBarId() {
        return barId;
    }

    public void setBarId(Integer barId) {
        this.barId = barId;
    }
}