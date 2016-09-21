package com.inHere.dto;

/**
 * 用户评论传输对象
 *
 * @lwh
 */
public class CommentDto {

    private Integer ext_type; // 类别
    private Integer item_id; // 记录编号
    private String content; // 评论内容

    public Integer getExt_type() {
        return ext_type;
    }

    public void setExt_type(Integer ext_type) {
        this.ext_type = ext_type;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "ext_type=" + ext_type +
                ", item_id=" + item_id +
                ", content='" + content + '\'' +
                '}';
    }
}
