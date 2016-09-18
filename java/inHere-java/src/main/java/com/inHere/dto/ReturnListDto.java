package com.inHere.dto;

import com.alibaba.fastjson.JSONArray;

/**
 * 列表传输对象
 *
 * @author lwh
 */
public class ReturnListDto {

    private Integer offset; // 数据库的行偏移
    private Integer limit; // 最大返回数量，等同page_size
    private Integer total; // 总数量
    private Integer total_page; // 总页面数量
    private JSONArray items; // 列表内容

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_page() {
        return total_page;
    }

    public void setTotal_page(Integer total_page) {
        this.total_page = total_page;
    }

    public JSONArray getItems() {
        return items;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ListDto [offset=" + offset + ", limit=" + limit
                + ", total=" + total + ", total_page=" + total_page + ", items=" + items + "]";
    }

}
