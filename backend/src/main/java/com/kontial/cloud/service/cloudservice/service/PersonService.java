package com.kontial.cloud.service.cloudservice.service;

import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.persistence.InMemoryDataSource;
import com.kontial.cloud.service.cloudservice.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private InMemoryDataSource inMemoryDataSource;

    @Autowired
    private PersonRepository personRepository;


    @PostConstruct
    public void init() {
        List<Person> persons = inMemoryDataSource.getAll();
        personRepository.saveAll(persons);

    }

    public List<String> getPersonNamesSummaryAsc () {
        List<Person> persons = getAll();
        // create hashmap to store key (Name) and value (occurence number)
        HashMap<String, Integer> occurence = new HashMap<>();
        for (Person person : persons) {
            String personName = person.getName();
            if (occurence.containsKey(personName)) {
                occurence.merge(personName, 0, (newValue, notUsedParam) -> newValue + 1);
            } else occurence.put(personName, 1);
        }
        List<Map.Entry<String, Integer>> result =
                occurence.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByKey())
                        .collect(Collectors.toList());
        List<String> output = new ArrayList<>();
        for (Map.Entry e : result) {
           output.add(e.getKey().toString().replace(" ", "space") + ": " + e.getValue());
        }
        return output;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
