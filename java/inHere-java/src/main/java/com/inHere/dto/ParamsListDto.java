package com.inHere.dto;

import java.util.Arrays;

import com.inHere.entity.Token;

/**
 * 参数传输对象
 * 
 * @author lwh
 *
 */
public class ParamsListDto {

	private Integer limit; // 返回条数
	private Integer offset;// 偏移量
	private Integer[] ext_type;// 标签过滤：1-快递，2-转让，3-帮忙, 4-丢失, 5-捡到, 6-走起
	private Integer item_id; // 记录编号
	private Integer label_id;// 问答+吐槽的标签编号

	private Integer is_end;// 结束过滤: 0-未结束，1-已结束
	private String creator;// 创建者过滤

	private String table_name;// 表名
	private String order_by;// 排序类别: time 、 praise
	private String order;// 倒序正序：desc 、asc

	// 传参token
	private Token tokenEntity;

	public Integer getItem_id() {
		return item_id;
	}

	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}

	public Integer getLabel_id() {
		return label_id;
	}

	public void setLabel_id(Integer label_id) {
		this.label_id = label_id;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer[] getExt_type() {
		return ext_type;
	}

	public void setExt_type(Integer[] ext_type) {
		this.ext_type = ext_type;
	}

	public Integer getIs_end() {
		return is_end;
	}

	public void setIs_end(Integer is_end) {
		this.is_end = is_end;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Token getTokenEntity() {
		return tokenEntity;
	}

	public void setTokenEntity(Token tokenEntity) {
		this.tokenEntity = tokenEntity;
	}

	@Override
	public String toString() {
		return "ParamsListDto [limit=" + limit + ", offset=" + offset + ", ext_type=" + Arrays.toString(ext_type)
				+ ", is_end=" + is_end + ", creator=" + creator + ", order_by=" + order_by + ", orcer=" + order + "]";
	}

}
