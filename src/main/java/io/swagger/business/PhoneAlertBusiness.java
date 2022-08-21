package io.swagger.business;

import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class PhoneAlertBusiness {
  private final PersonDao personDao;

  public PhoneAlertBusiness(PersonDao personDao) {
    this.personDao = personDao;
  }

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<PersonEntity> personEntities = personDao.findPersonByStationNumber(Integer.valueOf(stationNumber));
    return personDao.conversionListPersonEntityToPerson(personEntities);
  }
}
