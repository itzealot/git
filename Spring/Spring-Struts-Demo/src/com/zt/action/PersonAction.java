package com.zt.action;

import com.zt.service.PersonService;

public class PersonAction {
	
	private PersonService personService;

	public String execute() {
		System.out.println("execute...");
		personService.save();
		return "success";
	}
	
	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
}
