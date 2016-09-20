package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.constant.LabelEnum;
import com.inHere.dao.LabelMapper;
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
     * @param id 标签编号
     * @param name 标签名称
     * @return Label
     */
    @Transactional
    public Label createLabel(Integer id, String name) {
        // 查找相同的标签
        Label label = labelMapper.selectSameNameLabel(name);
        if( label == null ){
            label = new Label();
            label.setName(name);
            labelMapper.insertOneLabel(label);
            log.info("自动生成主键--->" + label.getId());
        }
        return label;
    }

    /**
     * 获取最火的标签
     *
     * @param type
     * @return
     */
    public JSONArray getHotLabel(Integer type, Integer offset, Integer limit) {
        List<Label> labels = labelMapper.selectHotLabel(type, offset, limit);
        JSONArray list = new JSONArray();

        if (type == Field.ExtType_InTeasing) {
            // 校内吐槽，第一条标签为小道消息
            JSONObject hearsay = new JSONObject();
            hearsay.put("id", LabelEnum.Hearsay.getId());
            hearsay.put("name", LabelEnum.Hearsay.getName());
            list.add(0, hearsay);
        }
        for (int i = 0; i < labels.size() && list.size() <= 5; i++) {
            JSONObject obj = new JSONObject();
            obj.put("id", labels.get(i).getId());
            obj.put("name", labels.get(i).getName());
            list.add(obj);
        }
        return list;
    }


}
