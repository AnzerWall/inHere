package test.inHere.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inHere.dao.TbUserMapper;
import com.inHere.entity.TbUser;

/**
 * Spring Test 和 JUnit 4 整合测试（单元测试）<br>
 * 继承 AbstractTransactionalJUnit4SpringContextTests 使用声明式事务管理，测试后数据回滚<br>
 * 继承 AbstractJUnit4SpringContextTests 无事务管理，测试后数据不回滚
 * 
 * @author lwh
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring-test测试套件
@ContextConfiguration("classpath:spring/spring-*.xml") // 配置文件
public class TbUserMapperTest extends AbstractTransactionalJUnit4SpringContextTests {

	Logger logger = Logger.getLogger(TbUserMapperTest.class);

	@Autowired
	private TbUserMapper userMapper;

//	@Test
	public void getUserByUserIdTest() {
		TbUser user = userMapper.getUserByUserId("18318749825");
		// userId不存在返回空
		logger.info("=========================================");
		logger.info("密码是：" + user.getPasswd());
		logger.info("=========================================");
	}

//	@Test
	public void insertUserTest() {
		TbUser user = new TbUser();
		user.setUserId("13202784103");
		user.setPasswd("sds");
		user.setSaltKey("23jks");
		int effactLine = userMapper.insertUser(user);
		logger.info("受影响行数: " + effactLine);
	}

}
