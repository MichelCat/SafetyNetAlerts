package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.PersonEntity;

@Repository
public class PersonDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findPersonByAddresses(List<String> addresses) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (addresses.contains(personEntity.getAddress())) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findChildByAddress(String address) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getAddress().equals(address)
          && personEntity.getAge() <= 18) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findOtherHouseholdPersonsByName(String firstName, String lastName, String address) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getLastName().equals(lastName)
          && !personEntity.getFirstName().equals(firstName)
          && personEntity.getAddress().equals(address)) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
  
  // -----------------------------------------------------------------------------------------------
  public List<PersonEntity> findPersonByCity(String city) {
    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getCity().equals(city)) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }
}
