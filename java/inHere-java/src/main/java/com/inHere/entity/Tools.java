package com.inHere.entity;

import java.util.Date;

/**
 * 工具实体类
 */
public class Tools {

    private Integer toolId;
    private String toolName;
    private String toolPic;
    private String toolUrl;
    private Date createTime;
    private Date updateTime;

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName == null ? null : toolName.trim();
    }

    public String getToolPic() {
        return toolPic;
    }

    public void setToolPic(String toolPic) {
        this.toolPic = toolPic == null ? null : toolPic.trim();
    }

    public String getToolUrl() {
        return toolUrl;
    }

    public void setToolUrl(String toolUrl) {
        this.toolUrl = toolUrl == null ? null : toolUrl.trim();
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
