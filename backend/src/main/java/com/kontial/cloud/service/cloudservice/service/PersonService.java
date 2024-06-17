package com.kontial.cloud.service.cloudservice.service;

import com.kontial.cloud.service.cloudservice.exception.PersonGeneralClientException;
import com.kontial.cloud.service.cloudservice.model.Person;
import com.kontial.cloud.service.cloudservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        Map<String, Integer> nameOccurrenceMap = new HashMap<>();
        persons.forEach(person -> nameOccurrenceMap.merge(person.getName(), 1, Integer::sum));
        return nameOccurrenceMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

    public List<Person> getAllWithYearAsBirthday() {
        return getAll().stream()
                .map(person -> new Person(person.getId(), person.getName(), person.getBirthday().substring(0, 4)))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Person> addPerson(Person person) throws Exception {
        Pattern idPattern = Pattern.compile("^[A-Za-z]\\d{4}$");
        boolean isIdPatternCorrect = idPattern.matcher(person.getId()).matches();
        boolean isNamePresent = person.getName().length() > 0;
        boolean isBirthDayFormatted = isValidDateFormat(person.getBirthday());
        boolean isIdUnique = personRepository.findById(person.getId()).isEmpty();

        if (isNamePresent && isBirthDayFormatted && isIdPatternCorrect && isIdUnique) {
            personRepository.save(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        } else
            throw new PersonGeneralClientException("Error while persisting a person - person is in incorrect format.");
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

    public ResponseEntity<Boolean> isIdUnique(String id) {
        boolean isIdUnique = personRepository.findById(id).isEmpty();
        if (isIdUnique) return ResponseEntity.status(HttpStatus.OK).body(true);
        return ResponseEntity.status(HttpStatus.OK).body(false);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public ResponseEntity<Person> updatePerson(Person person) {
        if (personRepository.findById(person.getId()).isPresent()) {
            personRepository.save(person);
            return ResponseEntity.status(204).body(person);
        } else throw new PersonGeneralClientException("Person you are attempting to edit, does not exist.");
    }

    public ResponseEntity<?> deletePersonById(String personId) {
        if (personRepository.findById(personId).isPresent()) {
            personRepository.deleteById(personId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else throw new PersonGeneralClientException("Person you are attempting to delete, does not exist.");
    }
}
