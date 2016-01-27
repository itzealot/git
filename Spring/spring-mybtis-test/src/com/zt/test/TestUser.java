package com.zt.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zt.bean.User;
import com.zt.mapper.UserMapper;
//使用Spring的测试框架
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml") //加载配置
public class TestUser {

	// 自动注入
	@Autowired
	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Test
	public void testSave() {
		User user = new User(-1, "Tom", new Date(), 3200);
		userMapper.save(user);
	}
}