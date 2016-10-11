package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ActivityValidator extends BaseValidator {

    Logger log = Logger.getLogger(getClass());

    /**
     * 列表参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getActivityList(ParamsListDto params) {
        return super.listValidator(params);
    }

    /**
     * @param request
     * @return
     */
    public ReturnBaseDto<JSONObject> publish(HttpServletRequest request) throws IOException {

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        String place = multiRequest.getParameter("place");
        if (place != null && place.length() > 20) {
            return result.setMessage("地点参数长度超限");
        }

        String msg = this.validator(request);
        if (msg != null) {
            return result.setMessage(msg);
        }

        return null;
    }

    /**
     * 广告发布校验
     *
     * @param request
     * @return
     */
    public ReturnBaseDto<JSONObject> ad(HttpServletRequest request) throws IOException {

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        String msg = this.validator(request);
        if (msg != null) {
            return result.setMessage(msg);
        }

        return null;
    }

    /**
     * 活动修改校验
     *
     * @param request
     * @return
     */
    public ReturnBaseDto<JSONObject> update(HttpServletRequest request) throws IOException {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        String title = multiRequest.getParameter("title");// 标题，不可为空
        String user_name = multiRequest.getParameter("user_name");// 组织名称, 不可为空
        List<MultipartFile> files = multiRequest.getFiles("file"); // 多图片
        String place = multiRequest.getParameter("place");
        String start_time = multiRequest.getParameter("start_time");
        String end_time = multiRequest.getParameter("end_time");

        if (place != null && place.length() > 20) {
            return result.setMessage("地点参数长度超限");
        }

        if (title != null && title.trim().length() > 50) {
            return result.setMessage("标题参数有错");
        }

        if (user_name != null && user_name.trim().length() > 30) {
            return result.setMessage("组织名称参数有错");
        }

        if (start_time != null && !Pattern.matches("^[0-9]{1,17}$", start_time)) {
            return result.setMessage("时间参数有错");
        }

        if (start_time != null && Long.parseLong(start_time) < System.currentTimeMillis()) {
            return result.setMessage("修改的开始时间必须是未来时间");
        }

        if (end_time != null && !Pattern.matches("^[0-9]{1,17}$", end_time)) {
            return result.setMessage("时间参数有错");
        }

        if (end_time != null && Long.parseLong(end_time) < System.currentTimeMillis()) {
            return result.setMessage("修改的结束时间必须是未来时间");
        }

        if (this.errPhoto(files, 1, 1024 * 1024)) {
            return result.setMessage("只能提交不超过1张图片，每张大小不能超过1M的哦~");
        }

        return null;
    }

    public String validator(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        String title = multiRequest.getParameter("title");// 标题，不可为空
        String user_name = multiRequest.getParameter("user_name");// 组织名称, 不可为空
        List<MultipartFile> files = multiRequest.getFiles("file"); // 多图片
        String start_time = multiRequest.getParameter("start_time");
        String end_time = multiRequest.getParameter("end_time");
        String url_type = multiRequest.getParameter("url_type");
        String url_content = multiRequest.getParameter("url_content");

        if (title == null || title.trim().length() > 50) {
            return "标题参数有错";
        }

        if (user_name == null || user_name.trim().length() > 30) {
            return "组织名称参数有错";
        }

        if (files.size() < 1) {
            return "必须提交封面哦~";
        }

        if (this.errPhoto(files, 1, 1024 * 1024)) {
            return "只能提交不超过1张图片，每张大小不能超过1M的哦~";
        }

        if (start_time == null || !Pattern.matches("^[0-9]{1,17}$", start_time)) {
            return "时间参数有错";
        }

        if (end_time == null || !Pattern.matches("^[0-9]{1,17}$", end_time)) {
            return "时间参数有错";
        }

        if (url_type == null || !Pattern.matches("[0|1]", url_type)) {
            return "内容类型有错";
        }

        if (url_content == null) {
            return "内容不能为空";
        }

        return null;
    }

}
