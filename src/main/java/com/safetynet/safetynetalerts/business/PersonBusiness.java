package com.safetynet.safetynetalerts.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.DateUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

/**
 * PersonBusiness is the service dealing with person
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class PersonBusiness {

  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private DateUtils dateUtils;

  /**
   * Add a person station
   * 
   * @param person An object person
   * @return Person, successful saved
   */
  public Person savePerson(final Person person) {
    PersonEntity personEntity = personUtils.conversionPersonToPersonEntity(person);
    return personUtils.conversionPersonEntityToPerson(personDao.save(personEntity));
  }

  /**
   * Delete an person
   * 
   * @param firstName - The first name of the person to delete
   * @param lastName - The last name of the person to delete
   * @return True, successful deleted
   */
  public boolean deletePerson(final String firstName, final String lastName) {
    return personDao.delete(firstName, lastName);
  }

  /**
   * Update an existing person
   * 
   * @param person An object person
   * @return Person, successful updated
   */
  public Person updatePerson(final Person person) {
    PersonEntity personEntity = personDao.findPersonByName(person.getFirstName(), person.getLastName());
    personEntity.setAddress(person.getAddress());
    personEntity.setPhoneNumber(person.getPhoneNumber());
    personEntity.setZip(person.getZipCode());
    personEntity.setCity(person.getCity());
    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(person.getBirthdate()));
    personEntity.setEmail(person.getEmail());
    return personUtils.conversionPersonEntityToPerson(personDao.update(personEntity));
  }
}
