package io.swagger.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class PersonBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  
  public Person save(final Person person) {
    PersonEntity personEntity = personUtils.conversionPersonToPersonEntity(person);
    return personUtils.conversionPersonEntityToPerson(personDao.save(personEntity));
  }

}
