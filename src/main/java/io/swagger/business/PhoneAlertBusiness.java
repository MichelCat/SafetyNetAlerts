package io.swagger.business;

import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class PhoneAlertBusiness {
  private final PersonDao personDao;
  private final FireStationDao fireStationDao;

  public PhoneAlertBusiness(PersonDao personDao, FireStationDao fireStationDao) {
    this.personDao = personDao;
    this.fireStationDao = fireStationDao;
  }

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    String stationAddress = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddress(stationAddress);
    return personDao.conversionListPersonEntityToPerson(personEntities);
  }
}
