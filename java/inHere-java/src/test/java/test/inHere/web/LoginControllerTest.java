package test.inHere.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.inHere.util.JsonFormatUtil;
import com.inHere.util.RestfulUtil;

/**
 * Spring Test 和 JUnit 4 整合测试（单元测试）<br>
 * 继承 AbstractTransactionalJUnit4SpringContextTests 使用声明式事务管理，测试后数据回滚<br>
 * 继承 AbstractJUnit4SpringContextTests 无事务管理，测试后数据不回滚
 * 
 * @author lwh
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring-test测试套件
@ContextConfiguration("classpath:spring/spring-*.xml") // 配置文件
public class LoginControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

	Logger logger = Logger.getLogger(getClass());

//	@Test
	public void loginTest() {
		RestfulUtil http = RestfulUtil.init("http://localhost");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("other", "你们好");
		map.put("other2", "你们好2");
		String result = http.doPut("/user/log_in/ni_menhao/123456/haha", JSON.toJSONString(map));
		JsonFormatUtil.printJson(result);
	}

}
