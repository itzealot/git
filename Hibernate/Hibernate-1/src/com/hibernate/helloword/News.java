package com.hibernate.helloword;

import java.util.Date;

public class News {
	private Integer id;
	private String title;
	private String autho;
	private Date date;
	
	
	public News() {
		// TODO Auto-generated constructor stub
	}

	
	public News(String title, String autho, Date date) {
		super();
		this.title = title;
		this.autho = autho;
		this.date = date;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutho() {
		return autho;
	}

	public void setAutho(String autho) {
		this.autho = autho;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", autho=" + autho
				+ ", date=" + date + "]";
	}
}
