package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Repository
public class PersonDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;

  // -----------------------------------------------------------------------------------------------
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
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findPersonByAddress(String address) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getAddress().equals(address)) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findChildByAddress(String address) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getAddress().equals(address)
          && personEntity.getAge() <= 18) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findOtherHouseholdPersonsByName(String firstName, String lastName) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getLastName().equals(lastName)
          && !personEntity.getFirstName().equals(firstName)) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
}
