package com.kontial.cloud.service.cloudservice.service;

import com.kontial.cloud.service.cloudservice.exception.PersonValidationException;
import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<String> getPersonNamesSummaryAsc() {
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


    public List<Person> getAllWithYearAsBirthday() {
        return getAll().stream()
                .map(person -> new Person(person.getId(), person.getName(), person.getBirthday().substring(0, 4)))
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> addPerson(Person person) throws Exception {
        Pattern idPattern = Pattern.compile("^[A-Za-z]\\d{4}$");
        boolean isIdPatternCorrect = idPattern.matcher(person.getId()).matches();
        boolean isNamePresent = person.getName().length() > 0;
        boolean isBirthDayFormatted = isValidDateFormat(person.getBirthday());
        boolean isIdUnique = personRepository.findById(person.getId()).isEmpty();

        if (isNamePresent && isBirthDayFormatted && isIdPatternCorrect && isIdUnique) {
            personRepository.save(person);
            return ResponseEntity.status(HttpStatus.OK).body("Person " + person.getName() + " has been added successfully.");

        } else {
            throw new PersonValidationException("Error while persisting a person - person is in incorrect format. ");
        }
    }

    public static boolean isValidDateFormat(String dateStr) {
        DateTimeFormatter expectedFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dateStr, expectedFormat);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public ResponseEntity<String> isIdUnique(String id) {
        boolean isIdUnique = personRepository.findById(id).isEmpty();
        if (isIdUnique) return ResponseEntity.status(HttpStatus.OK).body("unique");
        return ResponseEntity.status(HttpStatus.OK).body("taken");
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }


}
