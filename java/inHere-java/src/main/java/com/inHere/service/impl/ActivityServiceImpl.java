package com.inHere.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.dao.ActivityMapper;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.CommentMapper;
import com.inHere.dao.LabelMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.*;
import com.inHere.service.ActivityService;
import com.inHere.service.CommentService;
import com.inHere.service.CommonService;
import com.inHere.service.PraiseService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private AskReplyMapper askReplyMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PraiseService praiseService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommonService commonService;

    /**
     * 获取活动列表
     *
     * @param params
     * @return
     * @throws IOException
     */
    public JSONObject getList(ParamsListDto params) throws IOException {
        JSONObject data = new JSONObject();

        // 设置总页数
        Integer total = activityMapper.getCount(params.getTokenEntity().getSchool_id());
        Integer total_page = (total == 0 ? total : total / params.getLimit() + 1);

        data.put("offset", params.getOffset());
        data.put("limit", params.getLimit());
        data.put("total", total);
        data.put("total_page", total_page);
        data.put("items", this.getItems(params));

        log.info(data);
        return data;
    }

    public JSONArray getItems(ParamsListDto params) throws IOException {
        JSONArray items = new JSONArray();
        List<Activity> list = activityMapper.selectActivityList(params);
        for (Activity tmp : list) {
            JSONObject obj = new JSONObject();
            obj.put("id", tmp.getId());
            obj.put("ext_type", tmp.getExtType());
            // 获取扩展数据
            JSONObject ext_data = JSON.parseObject(tmp.getExtData());
            Object place = ext_data.get("place");

            // 解析图片
            JSONArray photos = commonService.photoResolution(tmp.getCoverImg());

            obj.put("cover_img", photos.size() > 0 ? photos.get(0) : "");
            obj.put("title", tmp.getTitle());
            obj.put("user_name", ext_data.get("user_name") != null ? ext_data.get("user_name") : null);
            obj.put("start_time", tmp.getStartTime());
            obj.put("end_time", tmp.getEndTime());
            obj.put("praise", praiseService.getPraiseSize(tmp.getPraise()));
            obj.put("praised", praiseService.praised(tmp.getPraise(), params.getTokenEntity().getUser_id()));

            obj.put("place", place != null ? place.toString() : null);
            obj.put("url_type", tmp.getUrlType());

            // url_type 为1时，内容返回空字符串
            if (tmp.getUrlType() == Field.URLType_Model) {
                tmp.setUrlContent("");
            }

            obj.put("url_content", tmp.getUrlContent());
            items.add(obj);
        }
        return items;
    }

    /**
     * 获取一条活动详情
     *
     * @param params
     * @return
     * @throws IOException
     */
    public JSONObject getOneActivity(ParamsListDto params) throws IOException {
        JSONObject data = new JSONObject();
        Activity activity = activityMapper.selectByPrimaryKey(params.getItem_id());
        if (activity != null) {
            data.put("id", activity.getId());
            data.put("ext_type", activity.getExtType());
            // 获取扩展数据
            JSONObject ext_data = JSON.parseObject(activity.getExtData());

            // 解析图片
            JSONArray photos = commonService.photoResolution(activity.getCoverImg());
            data.put("cover_img", photos.size() > 0 ? photos.get(0) : null);
            data.put("title", activity.getTitle());
            data.put("user_name", ext_data.get("user_name") != null ? ext_data.get("user_name").toString() : null);
            data.put("place", ext_data.get("place") != null ? ext_data.get("place").toString() : null);
            data.put("praise", praiseService.getPraiseSize(activity.getPraise()));
            data.put("praised", praiseService.praised(activity.getPraise(), params.getTokenEntity().getUser_id()));
            data.put("start_time", activity.getStartTime());
            data.put("end_time", activity.getEndTime());
            data.put("content", activity.getUrlContent());

            // 设置栏目类型
            params.setType(activity.getExtType());
            // 获取评论列表
            data.put("comment", commentService.getList(params));
        }
        return data;
    }

    /**
     * 获取广场页数据
     *
     * @return
     */
    public JSONObject square(String user_id) throws IOException {
        JSONObject data = new JSONObject();

        // 获取最新的一条活动
        Activity activity = activityMapper.selectLastItem();
        JSONObject ac = new JSONObject();
        // 解析图片
        JSONArray photos = commonService.photoResolution(activity.getCoverImg());
        ac.put("cover_img", photos.size() > 0 ? photos.get(0) : null);
        ac.put("title", activity.getTitle());
        // 获取扩展数据
        JSONObject ext_data = JSON.parseObject(activity.getExtData());
        ac.put("user_name", ext_data.get("user_name") != null ? ext_data.get("user_name").toString() : null);
        ac.put("start_time", activity.getStartTime());
        ac.put("end_time", activity.getEndTime());


        // 获取5条热门的回答列表
        JSONObject ask_reply = new JSONObject();
        List<AskReply> askReplys = askReplyMapper.selectHotQuestionOrTeasing(Field.ExtType_AskAnwser, 0, 6);
        JSONArray items = new JSONArray();
        if (askReplys.size() > 1) {
            for (int i = 1; i < askReplys.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("que_id", askReplys.get(i).getId());
                obj.put("que_title", askReplys.get(i).getTitle());
                items.add(obj);
            }
        }
        ask_reply.put("items", items);
        // 获取最火的回答
        JSONObject best_reply_answer = null;
        if (askReplys.size() > 0) {
            Comment comment = commentMapper.selectBestComment(askReplys.get(0).getExtType(), askReplys.get(0).getId());
            best_reply_answer = new JSONObject();
            best_reply_answer.put("que_id", askReplys.get(0).getId());
            best_reply_answer.put("que_title", askReplys.get(0).getTitle());
            best_reply_answer.put("reply_id", comment.getId());
            best_reply_answer.put("best_answer", comment.getContent());
        }
        ask_reply.put("best_reply", best_reply_answer);


        // 获取最热门的吐嘈标签
        JSONObject teasing = new JSONObject();
        List<Label> teasingLabels = labelMapper.selectHotLabel(Field.ExtType_InTeasing, 0, 5, null, null);
        JSONArray teasingItems = new JSONArray();
        if (teasingLabels.size() > 1) {
            for (int i = 1; i < teasingLabels.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("lab_id", teasingLabels.get(i).getId());
                obj.put("lab_name", teasingLabels.get(i).getName());
                teasingItems.add(obj);
            }
        }
        teasing.put("items", teasingItems);
        // 获取最火的吐嘈
        JSONObject best_reply_teasing = null;
        List<AskReply> inTeasing = askReplyMapper.selectHotQuestionOrTeasing(Field.ExtType_InTeasing, 0, 1);
        if (inTeasing.size() > 0) {
            best_reply_teasing = new JSONObject();
            best_reply_teasing.put("lab_id", inTeasing.get(0).getLabel().getId());
            best_reply_teasing.put("lab_name", inTeasing.get(0).getLabel().getName());
            best_reply_teasing.put("teasing_id", inTeasing.get(0).getId());
            best_reply_teasing.put("content", inTeasing.get(0).getContent());
        }
        teasing.put("best_teasing", best_reply_teasing);

        data.put("activity", ac);
        data.put("ask_reply", ask_reply);
        data.put("teasing", teasing);
        return data;
    }

    /**
     * 新建一个活动、广告
     *
     * @return
     */
    @Override
    public boolean createActivity(MultipartHttpServletRequest multiRequest, Integer type) throws IOException {
        Activity obj = this.newActivity(multiRequest, type);
        activityMapper.insertSelective(obj);
        return true;
    }

    /**
     * 新建一个活动
     *
     * @return
     */
    public Activity newActivity(MultipartHttpServletRequest multiRequest, Integer type) throws IOException {
        Activity obj = new Activity();

        String title = multiRequest.getParameter("title");// 标题，不可为空
        String place = multiRequest.getParameter("place");
        String user_name = multiRequest.getParameter("user_name");// 组织名称, 不可为空
        Long start_time = Long.parseLong(multiRequest.getParameter("start_time"));
        Long end_time = Long.parseLong(multiRequest.getParameter("end_time"));
        Integer url_type = Integer.parseInt(multiRequest.getParameter("url_type"));
        String url_content = multiRequest.getParameter("url_content");

        // 获取上传图片集合
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        // 解析图片
        JSONArray photos = commonService.resolverPhotos(fileList);

        obj.setTitle(title);
        obj.setExtType(type);
        JSONObject ext_data = new JSONObject();
        if (type.equals(Field.ExtType_Activity)) {
            ext_data.put("place", place);
        }
        // 活动组织
        ext_data.put("user_name", user_name);

        obj.setExtDataJSON(ext_data);
        obj.setCoverImg(photos.toJSONString());
        obj.setStartTime(new Date(start_time));
        obj.setEndTime(new Date(end_time));
        obj.setUrlType(url_type);
        obj.setUrlContent(url_content);
        obj.setIs_show(Field.Is_Show_Yes);
        // 发布用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        obj.setUserId(user.getUserId());

        return obj;
    }

    /**
     * 关闭一条活动或广告
     */
    public void closeActivity(Integer id) {
        activityMapper.closeActivity(id);
    }

    /**
     * 更新一条活动
     *
     * @param multiRequest
     * @throws IOException
     */
    @Transactional
    @Override
    public void updateActivity(MultipartHttpServletRequest multiRequest, Integer item_id) throws IOException {
        String title = multiRequest.getParameter("title");
        String place = multiRequest.getParameter("place");
        String user_name = multiRequest.getParameter("user_name");
        String start_time = multiRequest.getParameter("start_time");
        String end_time = multiRequest.getParameter("end_time");

        // 获取上传图片集合
        List<MultipartFile> fileList = multiRequest.getFiles("file");
        // 解析图片
        JSONArray photos = commonService.resolverPhotos(fileList);

        String url_content = multiRequest.getParameter("url_content");

        Activity activity = new Activity();
        activity.setTitle(title);

        JSONObject obj = new JSONObject();
        obj.put("place", place);
        obj.put("user_name", user_name);
        activity.setExtDataJSON(obj);
        activity.setCoverImg(photos.size() > 0 ? photos.toJSONString() : null);
        if (start_time != null)
            activity.setStartTime(new Date(Long.parseLong(start_time)));
        if (end_time != null)
            activity.setEndTime(new Date(Long.parseLong(end_time)));
        activity.setUrlContent(url_content);
        activity.setId(item_id);

        activityMapper.updateByPrimaryKeySelective(activity);
    }

}
