package test.inHere.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inHere.dto.ParamsListDto;
import com.inHere.dto.ReturnListDto;
import com.inHere.service.DemandService;

/**
 * Spring Test 和 JUnit 4 整合测试（单元测试）<br>
 * 继承 AbstractTransactionalJUnit4SpringContextTests 使用声明式事务管理，测试后数据回滚<br>
 * 继承 AbstractJUnit4SpringContextTests 无事务管理，测试后数据不回滚
 * 
 * @author lwh
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring-test测试套件
@ContextConfiguration("classpath:spring/spring-*.xml") // 配置文件
public class DemandServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private DemandService demandService;

	@Test
	public void getListOfTasks() {
		ParamsListDto params = null;
		ReturnListDto list = demandService.getList(params);
		System.out.println( list.toString() );
	}

}
