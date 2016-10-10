package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.entity.AskReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 吐槽+问答的校验类
 *
 * @author lwh
 */
@Component
public class AskReplyValidator extends BaseValidator {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private AskReplyMapper askReplyMapper;

    /**
     * 获取问答、吐槽列表参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getAskReplyList(ParamsListDto params) {

        // 参数中ext_type类型校验
        ReturnBaseDto<JSONObject> result = this.validatorExtType(params.getExt_type());
        if (result != null) return result;

        // 分页、排序参数校验
        return this.listValidator(params);
    }

    /**
     * 创建一个吐槽、问答的校验
     *
     * @param request
     * @return
     */
    public ReturnBaseDto<JSONObject> createAskReply(HttpServletRequest request) throws IOException {
        // flag = true 表示有错
        boolean flag = false;

        // 多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        // 检查form中是否有enctype="multipart/form-data"
        flag = !multipartResolver.isMultipart(request);
        if (flag) return this.result("不支持非表单类型提交数据");

        // 将request变成多部分Request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        String ext_type = multiRequest.getParameter("ext_type");
        String title = multiRequest.getParameter("title");
        String content = multiRequest.getParameter("content");
        String lab_name = multiRequest.getParameter("lab_name");
        // 获取上传图片集合
        List<MultipartFile> files = multiRequest.getFiles("file");

        log.info("--->ext_type:" + ext_type);
        // ext_type校验
        flag = this.isEmpty(ext_type);
        flag = flag || !Pattern.matches("^10|11|12$", ext_type);
        if (flag) {
            return this.result("参数类型有错哦~");
        }

        flag = this.errPhoto(files, 4, 1024 * 1024);
        if (flag) return this.result("只能提交不超过4张图片，每张大小不能超过1M的哦~");

        flag = this.isEmpty(lab_name);
        flag = flag || (lab_name.length() < 1 || lab_name.length() > 20);
        if (flag) {
            return this.result("标题参数有错哦~");
        }

        // 问答参数校验
        if (ext_type.equals(Field.ExtType_AskAnwser.toString())) {
            log.info("--->问答校验");
            // title 校验
            flag = this.isEmpty(title);
            flag = flag || this.isTooLong(title, 50);
            if (flag) {
                return this.result("标题参数有错哦~");
            }

            flag = content != null && this.isTooLong(content, 300);
            if (flag) return this.result("内容超长啦~");
        }

        // 吐槽参数校验
        if (ext_type.equals(Field.ExtType_InTeasing.toString()) || ext_type.equals(Field.ExtType_OutTeasing.toString())) {
            log.info("--->" + "吐槽校验");
            flag = this.isEmpty(content);
            flag = flag || this.isTooLong(content, 300);
            if (flag) return this.result("内容出错啦~");
        }
        return null;
    }

    /**
     * 获取最火标签列表校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getHotLabel(ParamsListDto params) {

        ReturnBaseDto<JSONObject> result = this.validatorExtType(params.getExt_type());
        if (result != null) return result;
        return this.listValidator(params);
    }

    public <T> ReturnBaseDto<T> validatorExtType(Integer[] ext_type) {
        // flag = true 表示有错
        boolean flag = false;
        // 参数中ext_type类型校验
        if (ext_type != null && ext_type.length > 0) {
            Integer type = ext_type[0];
            // 正确类型的判断
            flag = !(type == Field.ExtType_InTeasing || type == Field.ExtType_OutTeasing || type == Field.ExtType_AskAnwser);
        } else {
            flag = true;
        }
        if (flag) return this.result("模块类型有错误哦~");
        return null;
    }


    /**
     * 关注问题参数校验
     *
     * @param item_id
     * @return
     */
    public ReturnBaseDto<JSONObject> follow(Integer item_id) {
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        AskReply askReply = askReplyMapper.selectByPrimaryKey(item_id);
        if (askReply == null || !askReply.getExtType().equals(Field.ExtType_AskAnwser)) {
            return result.setMessage("参数有错");
        }

        return null;
    }

}
