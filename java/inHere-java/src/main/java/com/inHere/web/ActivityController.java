package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.Token;
import com.inHere.service.ActivityService;
import com.inHere.validator.ActivityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 活动
 *
 * @author lwh
 */
@RestController
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 获取活动列表 TODO 广告、活动、过滤检索
     *
     * @param params
     * @param token
     * @return
     * @throws IOException
     */
    @Authorization
    @Params(ActivityValidator.class)
    @RequestMapping(path = "/activity", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getActivityList(ParamsListDto params, @CurrentToken Token token)
            throws IOException {

        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        // 默认类型为活动和广告
        Integer[] ext_type = new Integer[]{Field.ExtType_Activity, Field.ExtType_AD};
        params.setExt_type(ext_type);
        params.setTokenEntity(token);

        JSONObject data = activityService.getList(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    @Authorization
    @RequestMapping(path = "/activity/{item_id}", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> getOneActivity(@PathVariable Integer item_id, @CurrentToken Token token)
            throws IOException {
        ParamsListDto params = new ParamsListDto();
        // 实体关联的评论列表分页初始化
        params.setItem_id(item_id);
        params.setLimit(10);
        params.setOffset(0);
        params.setTokenEntity(token);

        JSONObject data = activityService.getOneActivity(params);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 广场页接口
     *
     * @param token
     * @return
     * @throws IOException
     */
    @Authorization
    @RequestMapping(path = "/square", method = RequestMethod.GET)
    public ReturnBaseDto<JSONObject> square(@CurrentToken Token token) throws IOException {

        JSONObject data = activityService.square(token.getUser_id());

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(data);
        return result;
    }

    /**
     * 活动发布
     *
     * @return
     */
    @Params(ActivityValidator.class)
    @RequestMapping(path = "/admin/activity", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> publish(HttpServletRequest request) throws IOException {
        return this.activityAd(request, Field.ExtType_Activity);
    }

    /**
     * 广告发布
     *
     * @param request
     * @return
     * @throws IOException
     */
    @Params(ActivityValidator.class)
    @RequestMapping(path = "/admin/ad", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> ad(HttpServletRequest request) throws IOException {
        return this.activityAd(request, Field.ExtType_AD);
    }

    public ReturnBaseDto<JSONObject> activityAd(HttpServletRequest request, Integer type) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        // 提交数据库创建一条需求
        activityService.createActivity(multiRequest, type);
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 修改活动、广告
     *
     * @return
     */
    @Params(ActivityValidator.class)
    @RequestMapping(path = "/admin/activity/{item_id}", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> update(HttpServletRequest request, @PathVariable Integer item_id) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        // 提交数据库创建一条需求
        activityService.updateActivity(multiRequest, item_id);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 关闭活动
     *
     * @return
     */
    @RequestMapping(path = "/admin/activity/{item_id}", method = RequestMethod.DELETE)
    public ReturnBaseDto<JSONObject> close(@PathVariable Integer item_id) {
        activityService.closeActivity(item_id);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
