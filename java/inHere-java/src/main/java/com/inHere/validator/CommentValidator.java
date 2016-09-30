package com.inHere.validator;

import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.ActivityMapper;
import com.inHere.dao.AskReplyMapper;
import com.inHere.dao.DemandMapper;
import com.inHere.dto.CommentDto;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnBaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.regex.Pattern;

/**
 *
 */
@Component
public class CommentValidator extends BaseValidator {

    @Autowired
    private DemandMapper demandMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private AskReplyMapper askReplyMapper;

    /**
     * 获取评论列表参数校验
     *
     * @param params
     * @return
     */
    public ReturnBaseDto<JSONObject> getComments(ParamsListDto params) {

        // 参数中ext_type类型校验
        Integer[] ext_type = params.getExt_type();
        if (!(ext_type != null && ext_type.length > 0)) {
            return this.result("类型不能为空");
        }

        Integer item_id = params.getItem_id();
        if (item_id == null) {
            return this.result("记录编号不能为空");
        }

        // 分页、排序参数校验
        return this.listValidator(params);
    }

    /**
     * 发表评论校验
     *
     * @param commentDto
     * @return
     */
    public ReturnBaseDto<JSONObject> createComment(@RequestBody CommentDto commentDto) {
        // flag = true 时有错
        boolean flag = false;
        Integer ext_type = commentDto.getExt_type();
        Integer item_id = commentDto.getItem_id();
        String content = commentDto.getContent();

        if (ext_type == null || item_id == null) {
            return this.result("请填写完整信息！");
        }

        if (getObject(ext_type, item_id) == null) {
            return this.result("记录不存在，无法评论~");
        }

        flag = this.isEmpty(content);
        flag = flag || this.isTooLong(content, 300);
        if (flag) return this.result("评论内容可能为空或字数超长");

        return null;
    }

    /**
     * 校验ext_type中是否存在item_id
     *
     * @param ext_type
     * @param item_id
     * @return
     */
    public Object getObject(Integer ext_type, Integer item_id) {

        // 有求必应评论
        if (Pattern.matches("^1|2|3|4|5|6$", ext_type.toString())) {
            return demandMapper.selectByPrimaryKey(item_id);
        }

        // 活动或广告
        if (Pattern.matches("^8|9$", ext_type.toString())) {
            return activityMapper.selectByPrimaryKey(item_id);
        }

        // 吐槽或问答
        if (Pattern.matches("^10|11|12$", ext_type.toString())) {
            return askReplyMapper.selectByPrimaryKey(item_id);
        }

        return null;
    }

}
