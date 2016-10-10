package com.inHere.dto;

/**
 * 活动、广告传输实体类
 *
 * @author lwh
 */
public class ActivityDto {

    private String title;
    private String user_name;
    private String cover_img;
    private Long start_time;
    private Long end_time;
    private Integer url_type;
    private String url_content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Integer getUrl_type() {
        return url_type;
    }

    public void setUrl_type(Integer url_type) {
        this.url_type = url_type;
    }

    public String getUrl_content() {
        return url_content;
    }

    public void setUrl_content(String url_content) {
        this.url_content = url_content;
    }
}
