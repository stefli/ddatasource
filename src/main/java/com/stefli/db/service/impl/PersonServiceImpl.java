package com.stefli.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stefli.db.dao.IPersonDao;
import com.stefli.db.domain.Person;
import com.stefli.db.service.IPersonService;

@Service("personService")
public class PersonServiceImpl implements IPersonService {

	@Autowired
	IPersonDao personDao;
	
	public void createPerson(String name) {
		personDao.createPerson(name);
	}

	public List<Person> findAll() {
		return personDao.findAll();
	}

	
}
