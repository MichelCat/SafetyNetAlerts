package io.swagger.business;

import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class FirestationBusiness {
  private final PersonDao personDao;

  public FirestationBusiness(PersonDao personDao) {
    this.personDao = personDao;
  }

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<PersonEntity> personEntities = personDao.findPersonByStationNumber(Integer.valueOf(stationNumber));
    return personDao.conversionListPersonEntityToPerson(personEntities);
  }

  public int getAdultsLivingIn(List<Person> persons, String stationNumber) {
    return (int) persons.stream().filter(person -> person.getAge() > 18).count();
  }

  public int getChildrenLivingIn(List<Person> persons, String stationNumber) {
    return (int) persons.stream().filter(person -> person.getAge() <= 18).count();
  }
}
