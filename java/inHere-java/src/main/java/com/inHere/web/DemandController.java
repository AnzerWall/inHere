package com.inHere.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.annotation.Authorization;
import com.inHere.annotation.CurrentToken;
import com.inHere.annotation.Params;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Demand;
import com.inHere.entity.Token;
import com.inHere.service.CommonService;
import com.inHere.service.DemandService;
import com.inHere.validator.DemandValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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

    @Autowired
    private CommonService commonService;

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

        ReturnBaseDto<ReturnListDto> result = new ReturnBaseDto<ReturnListDto>();
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
        ReturnBaseDto<ReturnDemandDto> result = new ReturnBaseDto<ReturnDemandDto>();
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
     * TODO 数据库有更新，应放置事务安全中
     *
     * @return
     */
    @Authorization
    @Params(DemandValidator.class)
    @RequestMapping(path = "/demand", method = RequestMethod.POST)
    public ReturnBaseDto<JSONObject> createDemandItem(HttpServletRequest request, @CurrentToken Token token) throws IOException {
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

        Demand demand = this.createDemand(multiRequest, token);

        // 提交数据库创建一条需求
        demandService.createDemand(demand);

        ReturnBaseDto<JSONObject> result = new ReturnBaseDto<JSONObject>();
        result.setCode(Code.Success.getCode());
        result.setStatus(Code.Success.getStatus());
        return result;
    }

    /**
     * 创建一个需求
     *
     * @param multiRequest
     * @return
     */
    public Demand createDemand(MultipartHttpServletRequest multiRequest, Token token) throws IOException {
        Demand demand = new Demand();

        Integer ext_type = Integer.parseInt(multiRequest.getParameter("ext_type"));
        demand.setExtType(ext_type);
        String text = multiRequest.getParameter("text");
        demand.setText(text);
        // 初始化数据
        demand.setIsEnd(Field.IsEnd_NO);
        demand.setUserId(token.getUser_id());
        demand.setPraise(new JSONObject().toJSONString());

        // 获取上传图片集合
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        // 解析图片
        JSONArray photos = commonService.resolverPhotos(fileList);
        demand.setPhotos(photos.toJSONString());

        // 私有属性
        JSONObject ext_data = new JSONObject();
        // 处理快递私有属性、帮忙私有属性
        if (Field.ExtType_Express == ext_type || Field.ExtType_Help == ext_type) {
            String payStr = multiRequest.getParameter("pay"); // 酬金
            Double pay = payStr != null ? Double.parseDouble(payStr) : null;
            ext_data.put("pay", pay);
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理转让私有属性
        if (Field.ExtType_Sell == ext_type) {
            String priceStr = multiRequest.getParameter("price"); // 售价
            Double price = priceStr != null ? Double.parseDouble(priceStr) : null;
            String original_priceStr = multiRequest.getParameter("original_price"); // 原价
            Double original_price = original_priceStr != null ? Double.parseDouble(original_priceStr) : null;
            String quality = multiRequest.getParameter("quality"); // 成色
            String buy_time = multiRequest.getParameter("buy_time"); // 购买时间
            ext_data.put("price", price);
            ext_data.put("original_price", original_price);
            ext_data.put("quality", quality);
            ext_data.put("buy_time", buy_time);
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理丢失私有属性、找到私有属性
        if (Field.ExtType_Lost == ext_type || Field.ExtType_Found == ext_type) {
            String thing = multiRequest.getParameter("thing"); // 物品
            ext_data.put("thing", thing);
            log.info("--->" + thing);
            if (Field.ExtType_Lost == ext_type) {
                String lose_time = multiRequest.getParameter("lose_time"); // 丢失时间
                ext_data.put("lose_time", lose_time);
            }
            if (Field.ExtType_Found == ext_type) {
                String pickeup_time = multiRequest.getParameter("pickeup_time"); // 捡到时间
                ext_data.put("pickeup_time", pickeup_time);
            }
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理走起私有属性
        if (Field.ExtType_Dating == ext_type) {
            String place = multiRequest.getParameter("place"); // 活动地点

            String want_sexStr = multiRequest.getParameter("want_sex"); // 希望性别
            Integer want_sex = want_sexStr != null ? Integer.parseInt(want_sexStr) : Field.Sex_NotKnow;

            String start_time = multiRequest.getParameter("start_time"); // 开始时间
            String end_time = multiRequest.getParameter("end_time"); // 结束时间

            String per_cost_Str = multiRequest.getParameter("per_cost"); // 人均消费
            Double per_cost = per_cost_Str != null ? Double.parseDouble(per_cost_Str) : null;

            String gathering_time = multiRequest.getParameter("gathering_time"); // 集中时间
            String gathering_place = multiRequest.getParameter("gathering_place"); // 集中地点

            ext_data.put("place", place);
            ext_data.put("want_sex", want_sex);
            ext_data.put("start_time", start_time);
            ext_data.put("end_time", end_time);
            ext_data.put("per_cost", per_cost);
            ext_data.put("gathering_time", gathering_time);
            ext_data.put("gathering_place", gathering_place);
            ext_data.put("join_list", new JSONObject().toJSONString()); // 参与的人的列表 用hash存储
            demand.setExtDataJSON(ext_data);
            return demand;
        }
        return null;
    }

}