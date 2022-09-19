package io.swagger.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class PersonUtils {
  
  @Autowired
  private DateUtils dateUtils;

  public List<Person> conversionListPersonEntityToPerson(List<PersonEntity> personEntities) {
    List<Person> persons = new ArrayList<>();
    personEntities.forEach(e -> {
      persons.add(conversionPersonEntityToPerson(e));
    });
    return persons;
  }
  
  public Person conversionPersonEntityToPerson(PersonEntity personEntity) {
    var person = new Person();
    person.setId(personEntity.getId());
    person.setFirstName(personEntity.getFirstName());
    person.setLastName(personEntity.getLastName());
    person.setAddress(personEntity.getAddress());
    person.setPhoneNumber(personEntity.getPhoneNumber());
    person.setZipCode(personEntity.getZip());
    person.setCity(personEntity.getCity());
    person.setBirthdate(dateUtils.dateToStringDDMMYYYYConversion(personEntity.getBirthdate()));
    person.setEmail(personEntity.getEmail());
    person.setAge(personEntity.getAge());
    return person;
  }
  
  public PersonEntity conversionPersonToPersonEntity(Person person) {
    var personEntity = new PersonEntity();
    personEntity.setId(person.getId());
    personEntity.setFirstName(person.getFirstName());
    personEntity.setLastName(person.getLastName());
    personEntity.setAddress(person.getAddress());
    personEntity.setPhoneNumber(person.getPhoneNumber());
    personEntity.setZip(person.getZipCode());
    personEntity.setCity(person.getCity());
    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(person.getBirthdate()));
    personEntity.setEmail(person.getEmail());
    return personEntity;
  }
}
