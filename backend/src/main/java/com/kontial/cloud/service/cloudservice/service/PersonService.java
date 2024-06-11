package com.kontial.cloud.service.cloudservice.service;

import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private static List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("h2314", "Thomas"));
        persons.add(new Person("f5962", "Thomas"));
        persons.add(new Person("e5891", "Evelin"));
        persons.add(new Person("t7811", "Oliver"));
        persons.add(new Person("z5894", "Oliver"));
        persons.add(new Person("s8971", "Oliver"));
        persons.add(new Person("u5841", "Oliver"));
        persons.add(new Person("n2361", "Jennifer"));
        persons.add(new Person("w2054", "John"));
        persons.add(new Person("x9815", "Mike"));
        persons.add(new Person("c6358", "Henry"));
        persons.add(new Person("a2601", "Lucas"));
        persons.add(new Person("e8450", "Alice"));
        persons.add(new Person("w9640", "Alice"));
        persons.add(new Person("e5036", "Alice"));
        persons.add(new Person("t8405", "Andrea"));
        persons.add(new Person("u7840", "Ava"));
        persons.add(new Person("i6922", "Ava"));
    }

    @PostConstruct
    public void init() {
        personRepository.saveAll(persons);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
