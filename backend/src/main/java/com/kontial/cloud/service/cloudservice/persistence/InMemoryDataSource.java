package com.kontial.cloud.service.cloudservice.persistence;

import com.kontial.cloud.service.cloudservice.model.Person;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InMemoryDataSource {

    private static List<Person> persons;

    public static String generateRandomDateString() {
        int startYear = 1980;
        int endYear = 2005;
        Random random = new Random();
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int dayOfYear = 1 + random.nextInt(365);

        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(calendar.getTime());
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
}
