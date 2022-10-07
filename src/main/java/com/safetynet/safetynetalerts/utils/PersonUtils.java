package com.safetynet.safetynetalerts.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;

/**
 * PersonUtils is an Person object conversion utility class
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class PersonUtils {

  @Autowired
  private DateUtils dateUtils;

  /**
   * Conversion PersonEntity list to Person list
   * 
   * @param personEntities PersonEntity list
   * @return Person list
   */
  public List<Person> conversionListPersonEntityToPerson(List<PersonEntity> personEntities) {
    List<Person> persons = new ArrayList<>();
    personEntities.forEach(e -> {
      persons.add(conversionPersonEntityToPerson(e));
    });
    return persons;
  }

  /**
   * Conversion PersonEntity to Person
   * 
   * @param personEntity PersonEntity object
   * @return Person
   */
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

  /**
   * Conversion Person to PersonEntity
   * 
   * @param person Person object
   * @return PersonEntity
   */
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
