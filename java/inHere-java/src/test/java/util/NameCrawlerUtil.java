package util;

import org.apache.log4j.Logger;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring Test 和 JUnit 4 整合测试（单元测试）<br>
 * 继承 AbstractTransactionalJUnit4SpringContextTests 使用声明式事务管理，测试后数据回滚<br>
 * 继承 AbstractJUnit4SpringContextTests 无事务管理，测试后数据不回滚
 * 
 * @author lwh
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring-test测试套件
@ContextConfiguration("classpath:spring/spring-*.xml") // 配置文件
public class NameCrawlerUtil extends AbstractJUnit4SpringContextTests {

	Logger log = Logger.getLogger(getClass());

	@Test
	public void saveName() throws XPatherException {
		HtmlCleaner htmlCleaner = new HtmlCleaner();
		String url = "http://www.qmsjmfb.com";
		HTTPUtil http = HTTPUtil.getHTTP(url);
		String content = http.doGet("/");
//		log.info(content);
		TagNode htmlNode = htmlCleaner.clean(content);
		Object[] nodes = htmlNode.evaluateXPath("//*[@class='name_show']/li");
		if (nodes.length > 0) {
			TagNode nodeTmp = null;
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] instanceof TagNode) {
					nodeTmp = (TagNode) nodes[i];
					String name = nodeTmp.getText().toString();
					System.out.println(name);
				}
			}
		}
	}

}
