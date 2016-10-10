package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.inHere.dao.ToolsMapper;
import com.inHere.entity.Tools;
import com.inHere.service.ToolsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ToolsServiceImpl implements ToolsService {

    Logger log = Logger.getLogger(getClass());

    @Autowired
    private ToolsMapper toolsMapper;

    @Override
    public JSONArray getList(Integer school_id) {
        JSONArray items = new JSONArray();

        List<Tools> tools = toolsMapper.selectTools(school_id);
        for (Tools tool : tools) {
            log.info("--->" + tool.getToolPic());
        }

        return items;
    }

}
