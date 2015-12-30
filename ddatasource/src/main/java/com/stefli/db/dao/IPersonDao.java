package com.stefli.db.dao;

import java.util.List;

import com.stefli.db.domain.Person;

public interface IPersonDao {

	void createPerson(String name);

	List<Person> findAll();

}
