package com.inHere.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * 工具业务类
 */
public interface ToolsService {

    JSONObject getList(Integer school_id) throws IOException;

}
