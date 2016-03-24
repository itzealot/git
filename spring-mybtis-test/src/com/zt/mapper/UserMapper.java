package com.zt.mapper;

import java.util.List;

import com.zt.bean.User;

public interface UserMapper {

	public void save(User user);

	public void update(User user);

	public void delete(int id);

	public User findById(int id);

	public List<User> findAll();
}
