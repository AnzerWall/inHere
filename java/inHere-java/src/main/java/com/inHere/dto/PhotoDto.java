package com.inHere.dto;

/**
 * 图片传输对象
 * 
 * @author lwh
 *
 */
public class PhotoDto {

	private String min; // 小图数据base64
	private String src; // 大图url
	private Integer w; // 长
	private Integer h; // 高

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

}
