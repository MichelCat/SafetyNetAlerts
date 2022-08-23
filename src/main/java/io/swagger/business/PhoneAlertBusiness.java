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
public class PhoneAlertBusiness {
  private final PersonDao personDao;
  private final FireStationDao fireStationDao;
  
  @Autowired
  private PersonUtils personUtils;

  public PhoneAlertBusiness(PersonDao personDao, FireStationDao fireStationDao) {
    this.personDao = personDao;
    this.fireStationDao = fireStationDao;
  }

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
