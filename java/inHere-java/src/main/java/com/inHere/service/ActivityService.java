package com.inHere.service;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ParamsListDto;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

public interface ActivityService {

    /**
     * 获取活动列表
     *
     * @param params
     * @return
     */
    JSONObject getList(ParamsListDto params) throws IOException;

    /**
     * 获取一条活动详情
     *
     * @param params
     * @return
     */
    JSONObject getOneActivity(ParamsListDto params) throws IOException;

    /**
     * 获取广场页数据
     *
     * @param user_id
     * @return
     */
    JSONObject square(String user_id) throws IOException;

    /**
     * 创建一条活动、标签
     *
     * @param multiRequest
     * @return
     */
    boolean createActivity(MultipartHttpServletRequest multiRequest, Integer type) throws IOException;

    /**
     * 关闭一条活动或广告
     */
    void closeActivity(Integer id);

    /**
     * 修改广告、活动
     *
     * @param multiRequest
     * @param item_id
     * @throws IOException
     */
    void updateActivity(MultipartHttpServletRequest multiRequest, Integer item_id) throws IOException;
}
