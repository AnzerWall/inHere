package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.AskReplyUserMapper;
import com.inHere.dao.CommentMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.entity.*;
import com.inHere.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class AskReplyServiceImpl implements AskReplyService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private AskReplyMapper askReplyMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private AskReplyUserMapper askReplyUserMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private PraiseService praiseService;

    @Autowired
    private LabelService labelService;

    /**
     * 获取吐槽+问答的列表
     *
     * @param params 前台传输的参数
     * @return JSONObject
     * @throws IOException
     */
    public JSONObject getList(ParamsListDto params) throws IOException {
        JSONObject data = new JSONObject();
        log.info("进入AskReplyServiceImpl.getList()");

        // 为空时，查找全部标签数据
        if (params.getLabel_id() == null) {
            // 返回最火五条标签数据
            data.put("labels", labelService.getHotLabel(params.getType(), 0, 5));
        }

        // 创建返回列表对象
        ReturnListDto listDto = new ReturnListDto();
        // 获取总条数
        Integer total = askReplyMapper.getCount(params);
        Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);
        listDto.setLimit(params.getLimit());
        listDto.setOffset(params.getOffset());
        listDto.setTotal(total);
        listDto.setTotal_page(total_page);
        // 获取列表数据
        JSONArray items = this.getItems(params);
        listDto.setItems(items);

        // 返回列表数据
        data.put("list", listDto);
        return data;
    }

    /**
     * 获取列表数据
     *
     * @param params
     * @return
     * @throws IOException
     */
    private JSONArray getItems(ParamsListDto params) throws IOException {
        JSONArray items = new JSONArray();
        List<AskReply> list = askReplyMapper.selectList(params);

        for (AskReply obj : list) {
            JSONObject item = new JSONObject();
            item.put("id", obj.getId());
            item.put("ext_type", obj.getExtType());

            // 吐槽的私有数据
            if (params.getType() == Field.ExtType_InTeasing || params.getType() == Field.ExtType_OutTeasing) {
                item.put("ext_data", this.getTeasingExtData(obj, params.getTokenEntity()));
            }
            // 问答的私有数据
            if (params.getType() == Field.ExtType_AskAnwser) {
                item.put("ext_data", this.getAskAnwserDetailExtData(obj, params.getTokenEntity()));
            }

            item.put("user_id", obj.getUserId());
            item.put("label_id", obj.getLabel().getId());
            item.put("label_name", obj.getLabel().getName());
            item.put("create_time", obj.getCreateTime().getTime());
            items.add(item);
        }
        return items;
    }

    /**
     * 获取一条详细吐槽或问答信息
     *
     * @param params
     * @return
     * @throws IOException
     */
    public JSONObject getOneAskReply(ParamsListDto params) throws IOException {
        AskReply obj = askReplyMapper.selectOne(params);
        JSONObject data = new JSONObject();
        if (obj != null) {
            Map<String, Object> map = obj.toMap();

            // 拼接吐槽私有属性
            if (obj.getExtType() == Field.ExtType_InTeasing || obj.getExtType() == Field.ExtType_OutTeasing) {
                JSONObject ext_data = this.getTeasingExtData(obj, params.getTokenEntity());
                map.put("ext_data", ext_data);
            }
            // 拼接问答私有属性
            if (obj.getExtType() == Field.ExtType_AskAnwser) {
                JSONObject ext_data = this.getAskAnwserExtData(obj, params.getTokenEntity());
                map.put("ext_data", ext_data);
            }

            // 拼接评论列表
            params.setType(obj.getExtType());
            List<Comment> comments = commentMapper.selectList(params);

            // 设置当前需求实体的“评论列表”
            ReturnListDto listDto = new ReturnListDto();

            // 获取评论总条数
            Integer total = commentMapper.getCount(obj.getExtType(), obj.getId());
            Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);
            listDto.setLimit(params.getLimit());
            listDto.setOffset(params.getOffset());
            listDto.setTotal(total);
            listDto.setTotal_page(total_page);

            // 设置评论列表
            JSONArray commentArray = commentService.setItems(comments, params.getTokenEntity(), total - params.getOffset());
            listDto.setItems(commentArray);

            map.put("reply_list", listDto);
            data.putAll(map);
            return data;
        }
        return data;
    }

    /**
     * 获取吐槽私有数据
     *
     * @param obj
     * @return
     * @throws IOException
     */
    private JSONObject getTeasingExtData(AskReply obj, Token token) throws IOException {
        JSONObject data = new JSONObject();
        data.put("content", obj.getContent());
        data.put("comment_num", commentService.getCount(obj.getExtType(), obj.getId()));
        data.put("praise", praiseService.getPraiseSize(obj.getPraise()));
        data.put("praised", praiseService.praised(obj.getPraise(), token.getUser_id()));
        data.put("photos", commonService.photoResolution(obj.getPhotos()));
        return data;
    }

    /**
     * 获取问答私有数据
     *
     * @param obj
     * @param token
     * @return
     */
    private JSONObject getAskAnwserExtData(AskReply obj, Token token) throws IOException {
        JSONObject data = new JSONObject();
        data.put("title", obj.getTitle());
        data.put("content", obj.getContent());
        data.put("photos", commonService.photoResolution(obj.getPhotos()));
        AskReplyUser followUser = askReplyUserMapper.selectFollowUser(token.getUser_id(), obj.getId());
        // 判断用户是否关注该问答
        Integer follow = followUser == null ? 0 : 1;
        data.put("follow", follow);
        return data;
    }

    /**
     * 获取问答私有数据，包含最火的回答
     *
     * @param obj
     * @param token
     * @return
     */
    private JSONObject getAskAnwserDetailExtData(AskReply obj, Token token) {
        JSONObject data = new JSONObject();
        data.put("title", obj.getTitle());
        data.put("comment_num", commentService.getCount(obj.getExtType(), obj.getId()));

        // 获取最火的评论
        Comment comment = commentMapper.selectBestComment(obj.getExtType(), obj.getId());
        if (comment != null) {
            // 最火的评论的点赞数和用户是否点赞
            data.put("praise", praiseService.getPraiseSize(comment.getPraise()));
            data.put("praised", praiseService.praised(comment.getPraise(), token.getUser_id()));
            // 获取最火的答案返回
            JSONObject bestReply = new JSONObject();
            bestReply.put("id", comment.getId());
            bestReply.put("best_answer", comment.getContent());
            data.put("best_reply", bestReply);
        } else {
            // 还没评论
            data.put("praise", 0);
            data.put("praised", Field.Praised_NO);
            data.put("best_reply", null);
        }
        return data;
    }

    /**
     * 创建一个吐槽或问答资源
     */
    @Transactional
    public void createAskReply(MultipartHttpServletRequest multiRequest, Token token) throws IOException {
        AskReply askReply = new AskReply();

        Integer ext_type = Integer.parseInt(multiRequest.getParameter("ext_type"));
        askReply.setExtType(ext_type); // 类别
        askReply.setExtData(new JSONObject().toJSONString()); // 类别私有数据

        // 问答有标题
        if (Field.ExtType_AskAnwser == ext_type) {
            String title = multiRequest.getParameter("title");
            askReply.setTitle(title); // 标题
        }
        String content = multiRequest.getParameter("content");
        askReply.setContent(content); // 内容

        String lab_name = multiRequest.getParameter("lab_name");
        Label label = labelService.createLabel(lab_name);
        askReply.setLabelId(label.getId()); // 标签id

        // 标签检测，自定义或选择
        //Integer lab_id = Integer.parseInt(multiRequest.getParameter("lab_id"));

        // 自定义标签
        //if (lab_id == Field.Label_Custom) {
        //	String lab_name = multiRequest.getParameter("lab_name");
        //	Label label = labelService.createLabel(lab_id, lab_name);
        //	askReply.setLabelId(label.getId()); // 标签id
        //} else { // 选择标签
        //	askReply.setLabelId(lab_id); // 标签id
        //}

        // 获取上传图片集合
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        JSONArray photos = commonService.resolverPhotos(fileList);
        askReply.setPhotos(photos.toJSONString()); // 图片

        askReply.setUserId(token.getUser_id()); // 用户编号
        askReply.setPraise(new JSONObject().toJSONString()); // 点赞用户列表
        askReply.setLow(new JSONObject().toJSONString()); // 踩的用户列表

        askReplyMapper.insertSelective(askReply);
    }

    /**
     * 判断用户是否关注
     *
     * @param user_id
     * @param item_id
     * @return
     */
    @Override
    public boolean checkFollowed(String user_id, Integer item_id) {
        Map<String, Object> map = askReplyMapper.checkFollowed(user_id, item_id);
        if (map == null)
            return false;
        else
            return true;
    }

    /**
     * 关注问题
     *
     * @param user_id
     * @param item_id
     */
    @Override
    public void followAnIssue(String user_id, Integer item_id) {
        if (!checkFollowed(user_id, item_id)) {
            askReplyMapper.followAnIssue(user_id, item_id);
        }
    }

}
