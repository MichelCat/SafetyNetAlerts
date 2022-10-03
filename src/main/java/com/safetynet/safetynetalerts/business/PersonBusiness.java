package com.safetynet.safetynetalerts.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.DateUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

@Service
public class PersonBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private DateUtils dateUtils;
  
  public Person savePerson(final Person person) {
    PersonEntity personEntity = personUtils.conversionPersonToPersonEntity(person);
    return personUtils.conversionPersonEntityToPerson(personDao.save(personEntity));
  }

  public boolean deletePerson(final String firstName, final String lastName) {
    return personDao.delete(firstName, lastName);
  }

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
