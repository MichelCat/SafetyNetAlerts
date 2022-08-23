package io.swagger.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class PersonUtils {

  public List<Person> conversionListPersonEntityToPerson(List<PersonEntity> personEntities) {
    List<Person> persons = new ArrayList<>();
    personEntities.forEach(e -> {
      persons.add(conversionPersonEntityToPerson(e));
    });
    return persons;
  }
  
  public Person conversionPersonEntityToPerson(PersonEntity personEntity) {
    Person person = new Person();
    person.setId(personEntity.getId());
    person.setFirstName(personEntity.getFirstName());
    person.setLastName(personEntity.getLastName());
    person.setAddress(personEntity.getAddress());
    person.setPhoneNumber(personEntity.getPhoneNumber());
    person.setZipCode(personEntity.getZip());
    person.setCity(personEntity.getCity());
    person.setBirthdate(personEntity.getBirthdate().toString());
    person.setEmail(personEntity.getEmail());
    person.setAge(personEntity.getAge());
    return person;
  }
}
