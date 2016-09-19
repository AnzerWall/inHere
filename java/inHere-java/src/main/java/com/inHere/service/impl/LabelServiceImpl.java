package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.constant.Field;
import com.inHere.constant.LabelEnum;
import com.inHere.dao.LabelMapper;
import com.inHere.entity.Label;
import com.inHere.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标签业务
 *
 * @author lwh
 */
@Service
public class LabelServiceImpl implements LabelService {


    @Autowired
    private LabelMapper labelMapper;

    public Label searchLabel(Integer id, String name){
        Label label = null;



        return label;
    }


    /**
     * 获取最火的5条标签
     *
     * @param type
     * @return
     */
    public JSONArray getHotLabel(Integer type) {
        List<Label> labels = labelMapper.selectHotLabel(type);
        JSONArray list = new JSONArray();

        if (type == Field.ExtType_InTeasing) {
            // 校内吐槽，第一条标签为小道消息
            JSONObject hearsay = new JSONObject();
            hearsay.put("id", LabelEnum.Hearsay.getId());
            hearsay.put("name", LabelEnum.Hearsay.getName());
            list.add(0, hearsay);

            for (int i = 1; i < labels.size() && i < 5; i++) {
                JSONObject obj = new JSONObject();
                obj.put("id", labels.get(i).getId());
                obj.put("name", labels.get(i).getName());
                list.add(i, obj);
            }

        } else {
            for (int i = 0; i < labels.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("id", labels.get(i).getId());
                obj.put("name", labels.get(i).getName());
                list.add(i, obj);
            }
        }
        return list;
    }


}
