package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Code;
import com.inHere.constant.Field;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.DemandMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.Comment;
import com.inHere.entity.Demand;
import com.inHere.entity.Token;
import com.inHere.exception.SystemException;
import com.inHere.service.CommentService;
import com.inHere.service.CommonService;
import com.inHere.service.DemandService;
import com.inHere.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

/**
 * 有求必应业务逻辑
 *
 * @author lwh
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private CommentService commentService;

    /**
     * 获取列表
     *
     * @param params
     * @return
     * @throws IOException
     */
    public ReturnListDto getList(ParamsListDto params) throws IOException {
        log.info("调用getList()业务");
        ReturnListDto listDto = new ReturnListDto();
        List<Demand> demandList = demandMapper.selectByParams(params);

        // 获取总条数
        Integer total = demandMapper.getCount(params);
        Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);
        listDto.setLimit(params.getLimit());
        listDto.setOffset(params.getOffset());
        listDto.setTotal(total);
        listDto.setTotal_page(total_page);

        JSONArray items = this.setItems(demandList);
        listDto.setItems(items);
        return listDto;
    }

    /**
     * 获取一条有求必应
     *
     * @param params
     * @return
     * @throws IOException
     */
    public ReturnDemandDto selectOneById(ParamsListDto params) throws IOException {
        Demand demand = demandMapper.selectOneById(params);
        if (demand == null) {
            return new ReturnDemandDto();
        } else {
            // 当前实体拥有的评论列表
            List<Comment> comments = demand.getComments();

            // 获取“有求必应实体”DemandDto传输对象
            ReturnDemandDto demandDto = this.setDemandDto(demand);

            // 设置当前需求实体的“评论列表”
            ReturnListDto listDto = new ReturnListDto();

            // 获取评论总条数
            Integer total = commentMapper.getCount(demand.getExtType(), demand.getId());
            Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);
            listDto.setLimit(params.getLimit());
            listDto.setOffset(params.getOffset());
            listDto.setTotal(total);
            listDto.setTotal_page(total_page);

            // 设置评论列表
            JSONArray commentArray = commentService.setItems(comments, params.getTokenEntity(), total - params.getOffset());
            listDto.setItems(commentArray);

            // 有求必应实体添加对应评论列表返回。。。。
            demandDto.setComment(listDto);
            return demandDto;
        }
    }

    /**
     * 设置各类型Items
     *
     * @param demandList
     * @return
     * @throws IOException
     */
    public JSONArray setItems(List<Demand> demandList) throws IOException {
        JSONArray array = new JSONArray();
        for (Demand tmp : demandList) {
            ReturnDemandDto demandDto = this.setDemandDto(tmp);
            array.add(demandDto);
        }
        return array;
    }

    /**
     * 给ReturnDemandDto对象赋值
     *
     * @param tmp
     * @return
     * @throws IOException
     */
    public ReturnDemandDto setDemandDto(Demand tmp) throws IOException {
        ReturnDemandDto demandDto = new ReturnDemandDto();
        demandDto.setId(tmp.getId());
        demandDto.setExt_type(tmp.getExtType());
        demandDto.setText(tmp.getText());
        // 解析图片
        demandDto.setPhotos(commonService.photoResolution(tmp.getPhotos()));
        demandDto.setCreate_time(tmp.getCreateTime().getTime());
        demandDto.setUpdate_time(tmp.getUpdateTime().getTime());
        demandDto.setUser_id(tmp.getUserId());


        demandDto.setExt_data(JSONObject.parseObject(tmp.getExtData()));
        demandDto.setIs_end(tmp.getIsEnd());
        return demandDto;
    }

    /**
     * 创建一个需求
     *
     * @return
     */
    @Transactional
    @Override
    public boolean createDemand(MultipartHttpServletRequest multiRequest, Token token) throws IOException {
        Demand demand = this.newDemand(multiRequest, token);
        int line = demandMapper.insertSelective(demand, demand.getExtDataJSON());
        if (line > 0) {
            return true;
        } else {
            throw new SystemException(Code.Error.getCode(), Code.Error.getStatus(), "发布失败");
        }
    }

    /**
     * 创建一个Demand
     *
     * @param multiRequest
     * @return
     */
    public Demand newDemand(MultipartHttpServletRequest multiRequest, Token token) throws IOException {
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

        // 处理兼职私有属性、帮忙私有属性
        if (Field.ExtType_Express == ext_type || Field.ExtType_Help == ext_type) {
            String payStr = multiRequest.getParameter("pay"); // 酬金
            Double pay = (payStr != null) ? Double.parseDouble(payStr) : null;
            ext_data.put("pay", pay);
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理转让私有属性
        if (Field.ExtType_Sell == ext_type) {
            String priceStr = multiRequest.getParameter("price"); // 售价
            Double price = (priceStr != null) ? Double.parseDouble(priceStr) : null;

            String original_priceStr = multiRequest.getParameter("original_price"); // 原价
            Double original_price = (original_priceStr != null) ? Double.parseDouble(original_priceStr) : null;

            String qualityStr = multiRequest.getParameter("quality");
            Integer qualityInt = (qualityStr != null) ? Integer.parseInt(qualityStr) : null; // 成色
            String quality = Field.Quality.get(qualityInt);

            String buy_timeStr = multiRequest.getParameter("buy_time"); // 购买时间
            String buy_time = (buy_timeStr != null) ? DateUtil.longToDate(buy_timeStr) : null;

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
                ext_data.put("lose_time", (lose_time == null) ? null : DateUtil.longToDate(lose_time));
            }
            if (Field.ExtType_Found == ext_type) {
                String pickeup_time = multiRequest.getParameter("pickeup_time"); // 捡到时间
                ext_data.put("pickeup_time", (pickeup_time == null) ? null : DateUtil.longToDate(pickeup_time));
            }
            demand.setExtDataJSON(ext_data);
            return demand;
        }

        // 处理走起私有属性
        if (Field.ExtType_Dating == ext_type) {
            String place = multiRequest.getParameter("place"); // 活动地点

            String want_sexStr = multiRequest.getParameter("want_sex"); // 希望性别
            Integer want_sex = (want_sexStr != null) ? Integer.parseInt(want_sexStr) : Field.Sex_NotKnow;

            String start_time_str = multiRequest.getParameter("start_time"); // 开始时间
            String start_time =  DateUtil.longToDate(start_time_str);

            String end_time_str = multiRequest.getParameter("end_time"); // 结束时间
            String end_time = DateUtil.longToDate(end_time_str);

            String per_cost_Str = multiRequest.getParameter("per_cost"); // 人均消费
            Double per_cost = (per_cost_Str != null) ? Double.parseDouble(per_cost_Str) : null;

            String gathering_time_str = multiRequest.getParameter("gathering_time"); // 集中时间
            String gathering_time = (gathering_time_str != null) ? DateUtil.longToDate(gathering_time_str) : null;

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
