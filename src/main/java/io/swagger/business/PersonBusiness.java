package io.swagger.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.SafetyNetDataBase;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class PersonBusiness {
  
  private final PersonDao personDao;

  public PersonBusiness(PersonDao personDao) {
    this.personDao = personDao;
  }

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<PersonEntity> personEntities = personDao.findPersonByStationNumber(Integer.valueOf(stationNumber));
    return from(personEntities);
  }

  public int getAdultsLivingIn(List<Person> persons, String stationNumber) {
    int numberAdults = 0;
    for (Person person : persons) {
      if (person.getAge() > 18) {
        numberAdults++;
      }
    }
    return numberAdults;
  }

  public int getChildrenLivingIn(List<Person> persons, String stationNumber) {
    int numberChildren = 0;
    for (Person person : persons) {
      if (person.getAge() <= 18) {
        numberChildren++;
      }
    }
    return numberChildren;
  }

  private Person from(PersonEntity personEntity) {
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

  private List<Person> from(List<PersonEntity> personEntities) {
    List<Person> persons = new ArrayList<>();
    personEntities.forEach(e -> {
      persons.add(from(e));
    });
    return persons;
  }

}
