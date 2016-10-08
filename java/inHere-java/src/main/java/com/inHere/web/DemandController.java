package com.inHere.web;

import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Token;
import com.inHere.service.DemandService;
import com.inHere.validator.DemandValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 有求必应Controller
 *
 * @author lwh
 */
@RestController
public class DemandController {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private DemandService demandService;

    /**
     * @param params
     * @return
     * @throws IOException
     */
    @Authorization
    @Params(DemandValidator.class)
    @RequestMapping(path = "/demand", method = RequestMethod.GET)
    public ReturnBaseDto<ReturnListDto> getDemandList(ParamsListDto params, @CurrentToken Token token)
            throws IOException {
        // 初始化参数
        Integer offset = params.getOffset() == null ? 0 : params.getOffset();
        Integer limit = params.getLimit() == null ? 10 : params.getLimit();
        params.setOffset(offset);
        params.setLimit(limit);
        params.setTokenEntity(token);

        ReturnListDto listDto = demandService.getList(params);

        ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        result.setData(listDto);
        return result;
    }

    /**
     * @param item_id
     * @return
     * @throws IOException
     */
    @Authorization
    @RequestMapping(path = "/demand/{item_id}", method = RequestMethod.GET)
    public ReturnBaseDto<ReturnDemandDto> getDemandItem(@PathVariable Integer item_id, @CurrentToken Token token)
            throws IOException {
        log.info("参数" + item_id);
        ReturnBaseDto<ReturnDemandDto> result = new ReturnBaseDto<>();
        ParamsListDto params = new ParamsListDto();

        // 实体关联的评论列表分页初始化
        params.setItem_id(item_id);
        params.setLimit(5);
        params.setOffset(0);
        params.setTokenEntity(token);

        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        ReturnDemandDto data = demandService.selectOneById(params);
        result.setData(data == null ? new ReturnDemandDto() : data);
        return result;
    }

    /**
     * 创建需求资源
     *
     * @return
     */
    @Authorization
    @Params(DemandValidator.class)
    @RequestMapping(path = "/demand", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> createDemandItem(HttpServletRequest request, @CurrentToken Token token) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        // 提交数据库创建一条需求
        demandService.createDemand(multiRequest, token);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

}
