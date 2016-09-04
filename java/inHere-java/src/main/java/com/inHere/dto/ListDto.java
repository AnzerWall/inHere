package com.inHere.dto;

import java.util.List;

/**
 * 列表传输对象
 * 
 * @author lwh
 *
 */
public class ListDto<T> {

	private Integer page; // 页码，1开始
	private Integer page_size; // 传递给api的页面大小
	private Integer offset; // 数据库的行偏移
	private Integer limit; // 最大返回数量，等同page_size
	private Integer total; // 总数量
	private Integer total_page; // 总页面数量
	private List<T> items; // 列表内容

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

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

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
