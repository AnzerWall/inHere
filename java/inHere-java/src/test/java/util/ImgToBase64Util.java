package util;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
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
public class ImgToBase64Util {

	@Test
	public void testImgEncodeBase64() throws IOException {
		String src = "resources/beg_help/1-max.jpg";
		// 获取项目根路径和图片路径
		String root = this.getClass().getClassLoader().getResource("").toString();
		System.out.println( root );
		/*String minSrc = root + File.separator + "WEB-INF" + File.separator + src.replace("max", "min");

		// 小图转Base64传输
		File file = new File(minSrc);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		String min = Base64.encodeBase64String(buffer);
		System.out.println(min);*/
	}

}
