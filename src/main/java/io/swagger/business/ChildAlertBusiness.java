package io.swagger.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class ChildAlertBusiness {
  private final PersonDao personDao;

  public ChildAlertBusiness(PersonDao personDao) {
    this.personDao = personDao;
  }
  
  public List<Person> getChildLivingInArea(final String address) {
    List<PersonEntity> personEntities = personDao.findChildByAddress(address);
    return personDao.conversionListPersonEntityToPerson(personEntities);
  }

  public List<Person> getOtherHouseholdPersons(final String firstName, final String lastName) {
    List<PersonEntity> personEntities = personDao.findOtherHouseholdPersonsByName(firstName, lastName);
    return personDao.conversionListPersonEntityToPerson(personEntities);
  }
}
