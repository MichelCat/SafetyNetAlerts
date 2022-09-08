package io.swagger.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;
import io.swagger.utils.DateUtils;
import io.swagger.utils.PersonUtils;

@Service
public class PersonBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  public DateUtils dateUtils;
  
  public Person savePerson(final Person person) {
    PersonEntity personEntity = personUtils.conversionPersonToPersonEntity(person);
    return personUtils.conversionPersonEntityToPerson(personDao.save(personEntity));
  }

  public void deletePerson(final String firstName, final String lastName) {
    personDao.delete(firstName, lastName);
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
