package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.PersonUtils;

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
    List<PersonEntity> personEntities = personDao.findOtherHouseholdPersonsByNameAddress(firstName, lastName, address);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
