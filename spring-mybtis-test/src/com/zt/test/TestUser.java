package com.zt.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zt.bean.User;
import com.zt.mapper.UserMapper;
//ʹ��Spring�Ĳ��Կ��
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml") //��������
public class TestUser {

	// �Զ�ע��
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