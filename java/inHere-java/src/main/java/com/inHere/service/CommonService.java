package com.inHere.service;

import java.io.IOException;

import com.alibaba.fastjson.JSONArray;

public interface CommonService {

	/**
	 * 图片解析成List&lt;PhotoDto&gt;传输对象
	 * 
	 * @return
	 * @throws IOException
	 */
	public JSONArray photoResolution(String photos) throws IOException;

}
