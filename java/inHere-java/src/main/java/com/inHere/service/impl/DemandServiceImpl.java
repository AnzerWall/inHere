package com.inHere.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.inHere.dao.DemandMapper;
import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnDemandDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.dto.ReturnPhotoDto;
import com.inHere.entity.Demand;
import com.inHere.service.DemandService;

/**
 * 有求必应业务逻辑
 * 
 * @author lwh
 *
 */
@Service
@Transactional
public class DemandServiceImpl implements DemandService {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	private DemandMapper demandMapper;

	/**
	 * 获取列表
	 * 
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public ReturnListDto getList(ParamsListDto params) throws IOException {
		log.info("调用getList()业务");
		ReturnListDto listDto = new ReturnListDto();
		List<Demand> demandList = demandMapper.selectByParams(params);
		Integer page = (params.getOffset() / params.getLimit()) + 1;
		Integer page_size = params.getLimit();

		// 获取总条数
		Integer total = demandMapper.getCount(params);
		Integer total_page = total / params.getLimit();
		listDto.setPage(page);
		listDto.setPage_size(page_size);
		listDto.setLimit(params.getLimit());
		listDto.setOffset(params.getOffset());
		listDto.setTotal(total);
		listDto.setTotal_page(total_page);

		JSONArray items = this.setItems(demandList);
		listDto.setItems(items);
		return listDto;
	}

	/**
	 * 设置各类型Items
	 * 
	 * @param listDto
	 * @param demandList
	 * @return
	 * @throws IOException
	 */
	public JSONArray setItems(List<Demand> demandList) throws IOException {
		JSONArray array = new JSONArray();
		for (Demand tmp : demandList) {
			ReturnDemandDto demandDto = new ReturnDemandDto();
			demandDto.setId(tmp.getId());
			demandDto.setExt_type(tmp.getExtType());
			demandDto.setText(tmp.getText());
			// 解析图片
			demandDto.setPhotos(this.photoResolution(tmp.getPhotos()));
			demandDto.setCreate_time(tmp.getCreateTime().getTime());
			demandDto.setUpdate_time(tmp.getUpdateTime().getTime());
			demandDto.setUser_id(tmp.getUserId());
			demandDto.setExt_data(JSONObject.parseObject(tmp.getExtData()));
			demandDto.setIs_end(tmp.getIsEnd());
			array.add(demandDto);
		}
		return array;
	}

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
			JSONArray tmpArray = JSON.parseArray(photos);

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
