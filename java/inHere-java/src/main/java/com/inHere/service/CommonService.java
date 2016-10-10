package com.inHere.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommonService {

    /**
     * 图片解析成List&lt;PhotoDto&gt;传输对象
     *
     * @return
     * @throws IOException
     */
    JSONArray photoResolution(String photos) throws IOException;

    /**
     * 解析图片列表
     *
     * @return
     */
    JSONArray resolverPhotos(List<MultipartFile> fileList) throws IOException;

    /**
     * 获取所有学校
     *
     * @return
     */
    JSONObject getSchools();

}
