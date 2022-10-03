package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.PersonUtils;

@Service
public class CommunityEmailBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  
  public List<Person> getPersonByCity(final String city) {
    List<PersonEntity> personEntities = personDao.findPersonByCity(city);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
