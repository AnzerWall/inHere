package com.inHere.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.inHere.constant.Code;
import com.inHere.dao.CryptonymMapper;
import com.inHere.entity.Cryptonym;
import com.inHere.exception.SystemException;

/**
 * 文件上传处理
 * 
 * @author lwh
 *
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

	Logger log = Logger.getLogger(getClass());

	@Value("${file.maxSize}")
	private String maxSize;

	/**
	 * 单文件上传
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/one", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam CommonsMultipartFile file, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("/");
		log.info("根路径：" + root);

		log.info("文件大小：" + file.getSize());
		if (file.getSize() > Long.parseLong(maxSize)) {
			throw new SystemException(Code.UploadEx.getCode(), Code.UploadEx.getStatus(), "文件太大啦，小点吧！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upload", file.getOriginalFilename());
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		try {
			file.transferTo(newFile);
		} catch (IOException e) {
			String msg = "文件保存失败";
			throw new SystemException(Code.ServerError.getCode(), Code.ServerError.getStatus(), msg);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return map;
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(path = "/more", method = RequestMethod.POST)
	public Map<String, Object> upload2(HttpServletRequest request) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("upload2", "上传");

		long startTime = System.currentTimeMillis();

		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());

		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {

			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) { // 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = "E:/" + file.getOriginalFilename(); // 上传
					map.put(file.getName(), file.getOriginalFilename());
					file.transferTo(new File(path));
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		// throw new FileUploadException();
		return map;
	}
	

}
