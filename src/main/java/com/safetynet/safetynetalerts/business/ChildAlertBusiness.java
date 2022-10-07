package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.PersonUtils;

/**
 * ChildAlertBusiness is the service dealing with children living at an address
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class ChildAlertBusiness {

  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;

  /**
   * Get a list of children living at this address.
   * 
   * @param address Child's address
   * @return List of children
   */
  public List<Person> getChildLivingInArea(final String address) {
    List<PersonEntity> personEntities = personDao.findChildByAddress(address);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  /**
   * Get a list of other homemakers
   * 
   * @param firstName Child's first name
   * @param lastName Child's last name
   * @param address Child's address
   * @return List of other homemakers
   */
  public List<Person> getOtherHouseholdPersons(final String firstName, final String lastName, final String address) {
    List<PersonEntity> personEntities = personDao.findOtherHouseholdPersonsByNameAddress(firstName, lastName, address);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
