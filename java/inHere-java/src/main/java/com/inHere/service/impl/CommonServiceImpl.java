package com.inHere.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dto.ReturnPhotoDto;
import com.inHere.service.CommonService;

/**
 * 公共业务模块
 * 
 * @author lwh
 *
 */
@Service
public class CommonServiceImpl implements CommonService {

	/**
	 * 图片解析成List&lt;PhotoDto&gt;传输对象
	 * 
	 * @return
	 * @throws IOException
	 */
	public JSONArray photoResolution(String photos) throws IOException {
		JSONArray array = new JSONArray();
		// 判断是否有图片
		if (photos != null && !photos.trim().equals("")) {
			// 解析图片信息
			JSONArray tmpArray = null;
			try {
				tmpArray = JSON.parseArray(photos);
			} catch (Exception e) {
				tmpArray = new JSONArray();
				tmpArray.add(JSON.parseObject(photos));
			}

			int len = tmpArray.size();
			for (int i = 0; i < len; i++) {
				ReturnPhotoDto photo = new ReturnPhotoDto();

				JSONObject obj = tmpArray.getJSONObject(i);
				String src = obj.getString("src");

				// 获取项目根路径和图片路径
				String root = System.getProperty("inHere.root");
				String minSrc = root + File.separator + src.replace("max", "min").replace("/", File.separator);

				// 小图转Base64传输
				File file = new File(minSrc);
				FileInputStream inputFile = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				inputFile.read(buffer);
				inputFile.close();
				String min = Base64.encodeBase64String(buffer);

				Integer w = obj.getInteger("w");
				Integer h = obj.getInteger("h");

				photo.setMin(min);
				photo.setSrc(src);
				photo.setW(w);
				photo.setH(h);
				array.add(photo);
			}
			return array;
		}
		return array;
	}

}
