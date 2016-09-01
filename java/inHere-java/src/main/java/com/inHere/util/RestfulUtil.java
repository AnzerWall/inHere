package com.inHere.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * restful访问链接
 * 
 * @author lwh
 *
 */
public class RestfulUtil {

	/**
	 * HttpClient已经实现了线程安全。所以希望用户在实例化HttpClient时，也要支持为多个请求使用。
	 * http://www.yeetrack.com/?p=779
	 * 
	 * Rest模式有四种操作，
	 * 
	 * POST /uri 创建 DELETE /uri/xxx 删除 PUT /uri/xxx 更新或创建 GET /uri/xxx 查看
	 * 
	 */

	// HttpClient已经实现了线程安全。所以在实例化HttpClient时，可以支持为多个请求使用。
	private final CloseableHttpClient HttpClient = HttpClients.createDefault();

	private String domain;

	private RestfulUtil() {
	}

	/**
	 * @param domain
	 *            访问地址的域名或ip
	 * @return
	 */
	public static RestfulUtil init(String domain) {
		RestfulUtil http = new RestfulUtil();
		http.domain = domain;
		return http;
	}

	/**
	 * get方式访问服务器
	 * 
	 * @param servicePath
	 *            服务器提供的服务路径（注意不是整个路径，而是，相对于工程的路径）
	 *            例如：要访问http://127.0.0.1:10086/beebee/api/Demo/1
	 *            输入/api/Demo/1就可以了
	 * @return 服务器返回的json信息字符串
	 */
	public String doGet(String path) {
		String result = null;
		try {
			HttpGet get = new HttpGet(this.domain + path);
			get.addHeader("content-type", "application/json");
			get.addHeader("Accept", "application/json");
			HttpResponse response = this.HttpClient.execute(get);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * post方式访问服务器
	 * 
	 * @param path
	 *            服务器提供的服务路径（注意不是整个路径，而是，相对于工程的路径）
	 *            例如：要访问http://127.0.0.1:10086/beebee/api/Demo/1
	 *            输入/api/Demo/1就可以了
	 * @param params
	 *            json参数，是jsonObject形式
	 * @return 相应信息
	 */
	public String doPost(String path, String params) {
		String result = null;
		try {
			HttpPost post = new HttpPost(this.domain + path);
			post.addHeader("content-type", "application/json");
			post.addHeader("Accept", "application/json");
			if (params != null) {
				StringEntity entity = new StringEntity(params, "UTF-8");
				post.setEntity(entity);
			}
			HttpResponse response = this.HttpClient.execute(post);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * delete方式访问服务器
	 * 
	 * @param path
	 *            服务器提供的服务路径（注意不是整个路径，而是，相对于工程的路径）
	 *            例如：要访问http://127.0.0.1:10086/beebee/api/Demo/1
	 *            输入/api/Demo/1就可以了
	 * @return
	 */
	public String doDelete(String path) {
		String result = null;
		try {
			HttpDelete delete = new HttpDelete(this.domain + path);
			delete.addHeader("content-type", "application/json");
			delete.addHeader("Accept", "application/json");
			HttpResponse response = this.HttpClient.execute(delete);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * put方式访问服务器
	 * 
	 * @param path
	 *            服务器提供的服务路径（注意不是整个路径，而是，相对于工程的路径）
	 *            例如：要访问http://127.0.0.1:10086/beebee/api/Demo/1
	 *            输入/api/Demo/1就可以了
	 * @param params
	 *            参数对象
	 * @return
	 */
	public String doPut(String path, String params) {
		String result = null;
		try {
			HttpPut put = new HttpPut(this.domain + path);
			put.addHeader("content-type", "application/json");
			put.addHeader("Accept", "application/json");
			if (params != null) {
				StringEntity entity = new StringEntity(params, "UTF-8");
				put.setEntity(entity);
			}
			HttpResponse response = this.HttpClient.execute(put);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
