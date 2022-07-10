package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
      return   DB.stream()
              .filter(person -> person.getId().equals(id))
              .findFirst();
    }

    @Override
    public List<Person> getAllPerson() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMaybe = getPersonById(id);
        if(personMaybe.isEmpty()){
            return 0;
        }else {
            DB.remove(personMaybe.get());
            return 1;
        }
    }
}
