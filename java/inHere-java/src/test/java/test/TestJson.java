package test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class TestJson {

	@Test
	public void test() {
		String names = "[ '123','123', '456', '789', '999' ]";
		JSONArray array = JSON.parseArray(names);
		System.out.println(array.contains("123"));
		array.remove("123");
		// array.add("123");
		System.out.println(array.contains("123"));
	}

}
