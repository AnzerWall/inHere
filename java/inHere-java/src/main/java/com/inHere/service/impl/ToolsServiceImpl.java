package com.inHere.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.ToolsMapper;
import com.inHere.entity.Tools;
import com.inHere.service.ToolsService;
import com.inHere.util.FileUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@Service
public class ToolsServiceImpl implements ToolsService {

    Logger log = Logger.getLogger(getClass());

    @Value("${ip.root}")
    private String ip;

    @Autowired
    private ToolsMapper toolsMapper;

    @Override
    public JSONObject getList(Integer school_id) throws IOException {
        JSONObject data = new JSONObject();

        JSONArray items = new JSONArray();
        List<Tools> tools = toolsMapper.selectTools(school_id);
        data.put("total", tools.size());
        for (Tools tool : tools) {
            String path = System.getProperty("inHere.root") +
                    File.separator + tool.getToolPic().replace("/", File.separator);
            StringBuffer strBuf = new StringBuffer();
            FileUtil.readToBuffer(strBuf, path);
            Pattern p = Pattern.compile("width=\"(\\d+)px\".*height=\"(\\d+)px\"");
            Matcher m = p.matcher(strBuf.toString());
            Integer w = null;
            Integer h = null;
            if (m.find()){
                w = Integer.parseInt(m.group(1));
                h = Integer.parseInt(m.group(2));
            }
            // 图标
            JSONObject tmp = new JSONObject();
            tmp.put("src", ip + tool.getToolPic());
            tmp.put("w", w);
            tmp.put("h", h);

            JSONObject item = new JSONObject();
            item.put("svg", tmp);
            item.put("title", tool.getToolName());
            item.put("url", tool.getToolUrl());

            items.add(item);
        }
        data.put("items", items);
        return data;
    }

}
