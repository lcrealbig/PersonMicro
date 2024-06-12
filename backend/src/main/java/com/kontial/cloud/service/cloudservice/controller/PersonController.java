package com.kontial.cloud.service.cloudservice.controller;

import com.kontial.cloud.service.cloudservice.persistence.InMemoryDataSource;
import com.kontial.cloud.service.cloudservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api")
public class PersonController {

	@Autowired
	public PersonService personService;

//	@RequestMapping(value = "/persons", method = RequestMethod.GET)
//	public ResponseEntity<?> getAllPersons() {
//		var persons = personService.;
//		return ResponseEntity.ok(persons);
//	}

	@RequestMapping(value = "/persons/summary", method = RequestMethod.GET)
	public List<String> getAllPersonsSummary() {
		return personService.getPersonNamesSummaryAsc();

	}


//	@RequestMapping(value = "/persons", method = RequestMethod.POST)
//	public ResponseEntity<?> addPerson() {
//		var persons = inMemoryDataSource.getAll();
//		return ResponseEntity.ok(persons);
//	}

//	@RequestMapping(value = "/persons", method = RequestMethod.PUT)
//	public ResponseEntity<?> updatePerson() {
//		var persons = inMemoryDataSource.getAll();
//		return ResponseEntity.ok(persons);
//	}

//	@RequestMapping(value = "/persons", method = RequestMethod.DELETE)
//	public ResponseEntity<?> deletePerson() {
//		var persons = inMemoryDataSource.getAll();
//		return ResponseEntity.ok(persons);
//	}
}
