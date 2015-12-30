package com.stefli.db.service;

import java.util.List;

import com.stefli.db.domain.Person;

public interface IPersonService {
	
	public void createPerson(String name);

	public List<Person> findAll();
	
}
