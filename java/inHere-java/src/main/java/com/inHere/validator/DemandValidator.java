package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnListDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

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
    public ReturnBaseDto<JSONObject> createDemandItem(HttpServletRequest request) {
        // 多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());

        log.info("进入到--->创建一个需求的参数校验");
        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.InputErr.getCode());
        result.setStatus(Code.InputErr.getStatus());

        // 错误标识, flag == false 时有不满足条件存在
        boolean flag = true;

        // 检查form中是否有enctype="multipart/form-data"
        flag = multipartResolver.isMultipart(request);
        if (!flag) return result.setMessage("参数格式有错");

        // 将request变成多部分Request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        // ext_type 参数检验
        String ext_type_str = multiRequest.getParameter("ext_type");
        if (ext_type_str != null && !"".equals(ext_type_str.trim()))
            flag = ext_type_str.matches("^[0-9]*$");
        else flag = false;
        if (!flag) return result.setMessage("参数格式有错");

        return null;
    }

}
