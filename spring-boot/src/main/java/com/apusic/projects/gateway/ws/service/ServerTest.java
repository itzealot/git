package com.apusic.projects.gateway.ws.service;

import javax.xml.ws.Endpoint;

import com.apusic.projects.gateway.ws.service.impl.UserServiceImpl;

/*
 * 发布Web Service
 */
public class ServerTest {

	public static void main(String[] args) {
		String address = "http://localhost:8080/userServices";
		Endpoint.publish(address, new UserServiceImpl());
		System.out.println("发布webservice成功!");
	}
}