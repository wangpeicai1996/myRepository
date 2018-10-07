package com.pcwang.test;

import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pcwang.domain.User_Info;

/**
 * µ•≤‚spring»›∆˜≈‰÷√
 * @author Administrator
 *
 */
public class TestSpring {

	
	@Test
	public void testSpring() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
		User_Info user = context.getBean(User_Info.class);
		System.out.println(user.getUserName());
	}
	
}
