package com.gk.mapper;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gk.entity.User;
import com.gk.service.impl.UserServiceImpl;

//@RunWith(value=SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring-*.xml"})
public class UserMapperTest {

	ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
			"spring/spring-dao.xml", "spring/spring-service.xml");

	@Test
	public void testUserMapper() {
		UserMapper bean = classPathXmlApplicationContext.getBean(UserMapper.class);
		System.out.println(bean.selectById(1));

	}
	
	@Test
	public void testInsert() {
		
		User user=new User();
		user.setUserCreateTime(new Date());
		user.setUserName("hgx");
		user.setUserEmail("hgx@gui.com");
		UserMapper bean = classPathXmlApplicationContext.getBean(UserMapper.class);
		Integer insert = bean.insert(user);	
		System.out.println(insert);
	}
	
	
	@Test
	public void testUpdate() {
		
		User user=new User();
		user.setUserId(2);
		//user.setUserCreateTime(new Date());
		user.setUserName("hgx2323");
		user.setUserEmail("hgx@gui.com");
		UserMapper bean = classPathXmlApplicationContext.getBean(UserMapper.class);
		Integer insert = bean.updateById(user);	
		System.out.println(insert);
	}

}
