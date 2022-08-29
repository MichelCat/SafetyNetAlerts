package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class ChildAlertBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  
  public List<Person> getChildLivingInArea(final String address) {
    List<PersonEntity> personEntities = personDao.findChildByAddress(address);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  public List<Person> getOtherHouseholdPersons(final String firstName, final String lastName, final String address) {
    List<PersonEntity> personEntities = personDao.findOtherHouseholdPersonsByName(firstName, lastName, address);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
