package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class FirestationBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  public int getAdultsLivingIn(List<Person> persons, String stationNumber) {
    int numberAdults = 0;
    for (Person person : persons) {
      if (person.getAge() > 18) {
        ++numberAdults;
      }
    }
    return numberAdults;
  }

  public int getChildrenLivingIn(List<Person> persons, String stationNumber) {
    int numberChildreen = 0;
    for (Person person : persons) {
      if (person.getAge() <= 18) {
        ++numberChildreen;
      }
    }
    return numberChildreen;
  }
}
