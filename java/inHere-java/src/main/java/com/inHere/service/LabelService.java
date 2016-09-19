package com.inHere.service;

import com.alibaba.fastjson.JSONArray;

/**
 * 吐槽、问答的标签业务
 *
 * @author lwh
 */
public interface LabelService {


    /**
     * 获取最火的5条标签
     *
     * @param type
     * @return
     */
    public JSONArray getHotLabel(Integer type);

}
