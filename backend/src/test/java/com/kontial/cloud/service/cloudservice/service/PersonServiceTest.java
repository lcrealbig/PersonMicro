package com.kontial.cloud.service.cloudservice.service;

import com.kontial.cloud.service.cloudservice.model.Person;
import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
public class PersonServiceTest {

    @Autowired
    public PersonService personService;

    @Test
    @Name("Checking the actual output")
    void shouldReturnDuplicatedNamesTotalAscending() {
        //given - an expectation
        List<String> expected = List.of("Alice: 3","Andrea: 1","Ava: 2","Evelin: 1","Henry: 1","Jennifer: 1","John: 1","Lucas: 1","Mike: 1","Oliver: 4","Thomas: 2");

        //when - running actual method
        var actual = personService.getPersonNamesSummaryAsc();

        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Name("Checking the actual output - incomplete expectation")
    void shouldFail() {
        //given - an expectation
        List<String> expected = List.of("Alice: 3","Andrea: 1","Ava: 2","Evelin: 1","Henry: 1","John: 1","Lucas: 1","Mike: 1","Oliver: 4","Thomas: 2");

        //when - running actual method
        var actual = personService.getPersonNamesSummaryAsc();

        Assertions.assertNotEquals(expected.size(), actual.size());
        Assertions.assertNotEquals(expected, actual);
    }



}
