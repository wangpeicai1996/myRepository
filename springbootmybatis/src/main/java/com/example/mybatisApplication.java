package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableTransactionManagement//mybatis开启事务
@ComponentScan(basePackages= {"com.example.controller","com.example.service"})
@MapperScan(basePackages="com.example.dao")
public class mybatisApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(mybatisApplication.class, args);
	}

}
