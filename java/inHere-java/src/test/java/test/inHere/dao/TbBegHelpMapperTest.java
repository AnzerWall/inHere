package test.inHere.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inHere.entity.Demand;

/**
 * Spring Test 和 JUnit 4 整合测试（单元测试）<br>
 * 继承 AbstractTransactionalJUnit4SpringContextTests 使用声明式事务管理，测试后数据回滚<br>
 * 继承 AbstractJUnit4SpringContextTests 无事务管理，测试后数据不回滚
 * 
 * @author lwh
 */
@RunWith(SpringJUnit4ClassRunner.class) // spring-test测试套件
@ContextConfiguration("classpath:spring/spring-*.xml") // 配置文件
public class TbBegHelpMapperTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void insertOneBegHelp() {
		Demand help = new Demand();
		// help.setLableId(2);
		// help.setLabelData("{}");
		// help.setWordDesc("【转让】隐隐天朝龙瑞气， 悠悠步里古陵风。月镶阁宇星披彩， 天落霞波水点灯。逸影旋浮人月涌，
		// 流光隐幻舸雕腾。休言乱市无生色， 景境得时又一层。");
		// help.setHasResolved(0);
		// help.setUserIdBeg("LisaChan");
	}

}
