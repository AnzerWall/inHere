package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * Http协议操作类 <br>
 * 需要jar包：<br>
 * org.apache.httpcomponents httpclient 4.5.2
 * 
 * @author lwh
 *
 */
public class HTTPUtil {

	// HttpClient已经实现了线程安全。所以在实例化HttpClient时，可以支持为多个请求使用。
	private final CloseableHttpClient HttpClient = HttpClients.createDefault();

	private String serverDomain;

	private HTTPUtil() {
	}

	/**
	 * @param domain
	 *            访问地址的域名或ip
	 * @return
	 */
	public static HTTPUtil getHTTP(String domain) {
		HTTPUtil http = new HTTPUtil();
		http.serverDomain = domain;
		return http;
	}

	/**
	 * 使用 EntityUtils 不安全，除非来源信任, 不调用
	 * 
	 * @param url
	 * @return
	 */
	public String doGet(String url) {
		HttpGet httpGet = new HttpGet(this.serverDomain + url);
		if (url == null) {
			url = "";
		}
		CloseableHttpResponse response = null;
		try {
			response = HttpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			// return EntityUtils.toString(entity);
			return convertStreamToString(entity.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public String doPost(String url, Map<String, Object> params) {
		HttpPost httppost = new HttpPost(this.serverDomain + url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<String> keys = params.keySet();
		// Collection<Object> values = params.values();
		for (String key : keys) {
			formparams.add(new BasicNameValuePair(key, params.get(key).toString()));
		}
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

		httppost.setEntity(formEntity);
		CloseableHttpResponse response = null;
		try {
			httppost.setEntity(formEntity);
			response = this.HttpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			return convertStreamToString(entity.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 上传文件、参数，请封装到 Map 对象中
	 * 
	 * @param url
	 * @param params
	 */
	public String doUpload(String url, Map<String, Object> params) {
		HttpPost httppost = new HttpPost(this.HttpClient + url);
		CloseableHttpResponse response = null;
		try {
			// 创建实体，将请求 的参数封装封装到HttpEntity中
			EntityBuilder entityBuilder = EntityBuilder.create();
			entityBuilder.setContentEncoding("utf-8");
			entityBuilder.setContentType(ContentType.MULTIPART_FORM_DATA);

			// 分离参数和文件
			Set<String> keys = params.keySet();
			for (String key : keys) {
				Object unknow = params.get(key);
				if (unknow instanceof File) {
					entityBuilder.setFile((File) unknow);
				} else {
					entityBuilder.setParameters(new BasicNameValuePair(key, unknow.toString()));
				}
			}
			httppost.setEntity(entityBuilder.build());
			response = this.HttpClient.execute(httppost);
			HttpEntity entity = response.getEntity();
			return convertStreamToString(entity.getContent());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换stream为string字符串
	 * 
	 * @param is
	 *            输入流
	 * @return 流形成的字符串
	 */
	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		HTTPUtil http = HTTPUtil.getHTTP("https://www.baidu.com");
		String content = http.doGet("/");
		System.out.println(content);
	}
}
