package com.kontial.cloud.service.cloudservice.persistence;

import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.repository.PersonRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@Component
public class InMemoryDataSource {

    @Autowired
    private PersonRepository personRepository;
    private static List<Person> persons;

    public static String generateRandomDateString() {
        int startYear = 1980;
        int endYear = 2005;
        Random random = new Random();
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28);
        return LocalDate.of(year,month,day).toString();
    }


    static {
        persons = new ArrayList<>();
        persons.add(new Person("h2314", "Thomas", generateRandomDateString()));
        persons.add(new Person("f5962", "Thomas", generateRandomDateString()));
        persons.add(new Person("e5891", "Evelin", generateRandomDateString()));
        persons.add(new Person("t7811", "Oliver", generateRandomDateString()));
        persons.add(new Person("z5894", "Oliver", generateRandomDateString()));
        persons.add(new Person("s8971", "Oliver", generateRandomDateString()));
        persons.add(new Person("u5841", "Oliver", generateRandomDateString()));
        persons.add(new Person("n2361", "Jennifer", generateRandomDateString()));
        persons.add(new Person("w2054", "John", generateRandomDateString()));
        persons.add(new Person("x9815", "Mike", generateRandomDateString()));
        persons.add(new Person("c6358", "Henry", generateRandomDateString()));
        persons.add(new Person("a2601", "Lucas", generateRandomDateString()));
        persons.add(new Person("e8450", "Alice", generateRandomDateString()));
        persons.add(new Person("w9640", "Alice", generateRandomDateString()));
        persons.add(new Person("e5036", "Alice", generateRandomDateString()));
        persons.add(new Person("t8405", "Andrea", generateRandomDateString()));
        persons.add(new Person("u7840", "Ava", generateRandomDateString()));
        persons.add(new Person("i6922", "Ava", generateRandomDateString()));
    }

    public List<Person> getAll() {
        return persons;
    }

    @PostConstruct
    public void init() {
        List<Person> persons = getAll();
        personRepository.saveAll(persons);

    }
}
