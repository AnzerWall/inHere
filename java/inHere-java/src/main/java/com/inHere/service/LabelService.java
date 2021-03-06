package com.inHere.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Label;

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
    JSONArray getHotLabel(Integer type, Integer offset, Integer limit);

    /**
     * 创建一个标签
     *
     * @param id 标签编号
     * @param name 标签名称
     * @return Label
     */
    Label createLabel(String name);

    /**
     * 获取标签列表
     *
     * @param params
     * @return
     */
    JSONObject getHotList(ParamsListDto params);

}
