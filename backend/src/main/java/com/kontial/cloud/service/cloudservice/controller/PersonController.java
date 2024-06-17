package com.kontial.cloud.service.cloudservice.controller;

import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class PersonController {

    @Autowired
    public PersonService personService;

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPersons() {
        var persons = personService.getAllWithYearAsBirthday();
        return ResponseEntity.ok(persons);
    }

    @RequestMapping(value = "/persons/summary", method = RequestMethod.GET)
    public List<String> getAllPersonsSummary() {
        return personService.getPersonNamesSummaryAsc();

    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) throws Exception {
        return personService.addPerson(person);

    }

    @RequestMapping(value = "/persons/checkid/{personId}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> isIdUnique (@PathVariable String personId) {
        return personService.isIdUnique(personId);
    }


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
