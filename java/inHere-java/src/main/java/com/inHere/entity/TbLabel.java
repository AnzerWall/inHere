package com.inHere.entity;

public class TbLabel {
    private Integer labelId;

    private String labelName;

    private String labelConfig;

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public String getLabelConfig() {
        return labelConfig;
    }

    public void setLabelConfig(String labelConfig) {
        this.labelConfig = labelConfig == null ? null : labelConfig.trim();
    }
}