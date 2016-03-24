package com.zt.bean;

import java.util.Date;

public class User {

	private int id;
	private String name;
	private Date birthday;
	private float salary;

	public User() {
		super();
	}

	public User(int id, String name, Date birthday, float salary) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday
				+ ", salary=" + salary + "]";
	}

}
