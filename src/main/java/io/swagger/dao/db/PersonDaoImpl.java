package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.PersonEntity;

@Repository
public class PersonDaoImpl implements PersonDao {

  private static SortedSet<PersonEntity> personEntities = new TreeSet<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public SortedSet<PersonEntity> getPersonEntities() {
    return personEntities;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public PersonEntity findPersonByName(String firstName, String lastName) {
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        return personEntity;
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findPersonByAddresses(List<String> addresses) {
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (addresses.contains(personEntity.getAddress())) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findChildByAddress(String address) {
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getAddress().equals(address)
          && personEntity.getAge() <= 18) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findOtherHouseholdPersonsByName(String firstName, String lastName, String address) {
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getLastName().equals(lastName)
          && !personEntity.getFirstName().equals(firstName)
          && personEntity.getAddress().equals(address)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findPersonByCity(String city) {
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getCity().equals(city)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName) {
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getLastName().equalsIgnoreCase(lastName)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public PersonEntity save(PersonEntity personEntity) {
    personEntities.add(personEntity);
    return personEntity;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public PersonEntity update(PersonEntity personEntity) {
    personEntities.remove(personEntity);
    personEntities.add(personEntity);
    return personEntity;
  }

}
