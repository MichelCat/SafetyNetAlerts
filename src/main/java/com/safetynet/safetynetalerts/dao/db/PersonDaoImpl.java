package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;

@Repository
public class PersonDaoImpl implements PersonDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoImpl.class);
  
  private static List<PersonEntity> personEntities = new ArrayList<>();
  private static Integer personSequence = 0;

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    personSequence = 0;
    personEntities.clear();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public PersonEntity findPersonByName(String firstName, String lastName) {
    LOGGER.debug("Search query by fire first name and last name ({}, {}).", firstName, lastName);
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        return personEntity;
      }
    }
    LOGGER.warn("Non-existent person ({}, {}).", firstName, lastName);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findPersonByAddresses(List<String> addresses) {
    LOGGER.debug("Search query by address list ({}).", addresses);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      for (String address : addresses) {
        if (address.equalsIgnoreCase(personEntity.getAddress())) {
          returnList.add(personEntity);
        }
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findChildByAddress(String address) {
    LOGGER.debug("Search query for children living at an address ({}).", address);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getAddress().equalsIgnoreCase(address)
          && personEntity.getAge() <= 18) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findOtherHouseholdPersonsByNameAddress(String firstName, String lastName, String address) {
    LOGGER.debug("Search query for other household members ({}, {}).", firstName, lastName, address);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getLastName().equalsIgnoreCase(lastName)
          && !personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getAddress().equalsIgnoreCase(address)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findPersonByCity(String city) {
    LOGGER.debug("Search query by city ({}).", city);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getCity().equalsIgnoreCase(city)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName) {
    LOGGER.debug("Search query for people with the same name ({}, {}).", firstName, lastName);
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
  public PersonEntity save(PersonEntity newPersonEntity) {
    String newFirstName = newPersonEntity.getFirstName();
    String newLastName = newPersonEntity.getLastName();
    
    LOGGER.debug("Query add person ({}, {}).", newFirstName, newLastName);
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(newFirstName)
          && personEntity.getLastName().equalsIgnoreCase(newLastName)) {
        LOGGER.warn("Unable to add existing person ({}, {}).", newFirstName, newLastName);
        return null;
      }
    }
    ++ personSequence;
    newPersonEntity.setId(personSequence);
    if (personEntities.add(newPersonEntity) == false) {
      LOGGER.error("Unable to add non-existent person ({}, {}).", newFirstName, newLastName);
      return null;
    }
    return newPersonEntity;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public PersonEntity update(PersonEntity newPersonEntity) {
    String firstName = newPersonEntity.getFirstName();
    String lastName = newPersonEntity.getLastName();
    
    LOGGER.debug("Query update person ({}, {}).", firstName, lastName);
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        if (personEntities.remove(personEntity) == false) {
          LOGGER.error("Unable to remove the old person ({}, {}).", firstName, lastName);
          return null;
        }
        if (personEntities.add(newPersonEntity) == false) {
          LOGGER.error("Unable to add new person ({}, {}).", firstName, lastName);
          return null;
        }
        return newPersonEntity;
      }
    }
    LOGGER.warn("Updated a non-existent person ({}, {}).", firstName, lastName);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public boolean delete(String firstName, String lastName) {
    LOGGER.debug("Query delete person ({}, {}).", firstName, lastName);
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        if (personEntities.remove(personEntity) == false) {
          LOGGER.error("Unable to delete existing person ({}, {}).", firstName, lastName);
          return false;
        }
        return true;
      }
    }
    LOGGER.warn("Removed a non-existent person ({}, {}).", firstName, lastName);
    return false;
  }
}
