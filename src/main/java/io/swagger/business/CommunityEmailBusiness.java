package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class CommunityEmailBusiness {
  private final PersonDao personDao;
  
  @Autowired
  private PersonUtils personUtils;

  public CommunityEmailBusiness(PersonDao personDao) {
    this.personDao = personDao;
  }
  
  public List<Person> getPersonByCity(final String city) {
    List<PersonEntity> personEntities = personDao.findPersonByCity(city);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
