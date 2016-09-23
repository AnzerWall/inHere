package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnListDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 要求校验方法名与@Params拦截的方法的名字要相同, 校验方法的参数有且只能有一个，参数类型和返回类型都要和拦截方法的一样
 *
 * @author lwh
 */
@Component
public class DemandValidator extends BaseValidator {

    Logger log = Logger.getLogger(getClass());

    /**
     * DemandController.getDemandList()方法的校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<ReturnListDto> getDemandList(ParamsListDto params) {
        log.info("进入到--->获取需求列表的校验");
        return null;
    }

    /**
     * 创建一个需求的参数校验
     *
     * @param request
     * @return
     */
    public ReturnBaseDto<JSONObject> createDemandItem(HttpServletRequest request) throws IOException {
        // 错误标识, flag == true 时有不满足条件存在
        boolean flag = false;
        // 多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        // 检查form中是否有enctype="multipart/form-data"
        flag = multipartResolver.isMultipart(request);
        if (!flag) return this.result("不支持非表单类型提交数据");

        // 将request变成多部分Request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        String ext_type = multiRequest.getParameter("ext_type"); // 类别
        String text = multiRequest.getParameter("text"); // 文字描述
        List<MultipartFile> files = multiRequest.getFiles("file"); // 多图片
        String payS = multiRequest.getParameter("pay"); // 酬金
        String price = multiRequest.getParameter("price"); // 售价
        String original_price = multiRequest.getParameter("original_price"); // 原价
        String quality = multiRequest.getParameter("quality"); // 成色
        String buy_time = multiRequest.getParameter("buy_time"); // 购买时间
        String thing = multiRequest.getParameter("thing"); // 物品
        String lose_time = multiRequest.getParameter("lose_time"); // 丢失时间
        String pickeup_time = multiRequest.getParameter("pickeup_time"); // 捡到时间
        String place = multiRequest.getParameter("place"); // 活动地点
        String want_sex = multiRequest.getParameter("want_sex"); // 希望性别
        String start_time = multiRequest.getParameter("start_time"); // 开始时间
        String end_time = multiRequest.getParameter("end_time"); // 结束时间
        String per_cost = multiRequest.getParameter("per_cost"); // 人均消费
        String gathering_time = multiRequest.getParameter("gathering_time"); // 集中时间
        String gathering_place = multiRequest.getParameter("gathering_place"); // 集中地点

        // 发布类型校验
        flag = this.isEmpty(ext_type);
        flag = flag || this.notDigital(ext_type);
        if (flag) return this.result("发布类型要提交哦~");

        // 文字描述校验
        flag = this.isEmpty(text);// 非空
        flag = flag || this.isTooLong(text, 200);// 限200字
        if (flag) return this.result("文字描述有问题哦~看下填了没或超字数");

        flag = this.errPhoto(files, 4, 1 * 1024 * 1024);
        if (flag) return this.result("只能提交不超过4张图片，大小不能超过1M的哦~");

        if (Field.ExtType_Dating.toString().equals(ext_type)) {

        }

        return null;
    }

}
