package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.constant.LabelEnum;
import com.inHere.dao.LabelMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.entity.Label;
import com.inHere.service.LabelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标签业务
 *
 * @author lwh
 */
@Service
public class LabelServiceImpl implements LabelService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private LabelMapper labelMapper;

    /**
     * 创建一个标签
     *
     * @param id   标签编号
     * @param name 标签名称
     * @return Label
     */
    @Transactional
    public Label createLabel(Integer id, String name) {
        // 查找相同的标签
        Label label = labelMapper.selectSameNameLabel(name);
        if (label == null) {
            label = new Label();
            label.setName(name);
            labelMapper.insertOneLabel(label);
            log.info("自动生成主键--->" + label.getId());
        }
        return label;
    }

    /**
     * 获取最火的标签列表
     *
     * @param type
     * @return
     */
    public JSONArray getHotLabel(Integer type, Integer offset, Integer limit) {
        // 排除小道消息
        List<Label> labels = labelMapper.selectHotLabel(type, offset, limit, LabelEnum.Hearsay.getId(), null);
        JSONArray list = new JSONArray();

        // 校内吐槽
        if (type == Field.ExtType_InTeasing) {
            limit = limit - 1;
            // 只搜小道消息
            List<Label> labList = labelMapper.selectHotLabel(type, 0, 1, null, LabelEnum.Hearsay.getId());
            Label labHearsay = null;
            if (labList.size() > 0) labHearsay = labList.get(0);

            JSONObject hearsay = new JSONObject();
            hearsay.put("id", LabelEnum.Hearsay.getId());
            hearsay.put("name", LabelEnum.Hearsay.getName());
            hearsay.put("comment_num", labHearsay.getComment_num());
            list.add(0, hearsay);
        }
        for (int i = 0; i < labels.size() && i < limit; i++) {
            JSONObject obj = new JSONObject();
            obj.put("id", labels.get(i).getId());
            obj.put("name", labels.get(i).getName());
            obj.put("comment_num", labels.get(i).getComment_num());
            list.add(obj);
        }
        return list;
    }

    /**
     * 获取最火的标签列表
     *
     * @param params
     * @return
     */
    public JSONObject getHotList(ParamsListDto params) {
        JSONObject data = new JSONObject();

        // 设置总页数
        Integer total = labelMapper.getCount(params.getType());
        Integer total_page = total / params.getLimit() + 1;

        data.put("offset", params.getOffset());
        data.put("limit", params.getLimit());
        data.put("total", total);
        data.put("total_page", total_page);
        data.put("items", this.getHotLabel(params.getType(), params.getOffset(), params.getLimit()));

        return data;
    }


}
