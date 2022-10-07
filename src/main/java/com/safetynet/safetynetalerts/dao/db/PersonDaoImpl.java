package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;

/**
 * PersonDaoImpl manages the PersonEntity list
 * 
 * @author MC
 * @version 1.0
 */
@Repository
public class PersonDaoImpl implements PersonDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonDaoImpl.class);
  
  private static List<PersonEntity> personEntities = new ArrayList<>();
  private static Integer personSequence = 0;

  /**
   * Clear PersonEntity list
   */
  @Override
  public void clearTable() {
    personSequence = 0;
    personEntities.clear();
  }

  /**
   * Search person by first name and last name
   * 
   * @param firstName First name
   * @param lastName Last name
   * @return Person, if successful search, and null if not
   */
  @Override
  public PersonEntity findPersonByName(String firstName, String lastName) {
    LOGGER.debug("Person search query by fire first name and last name ({}, {}).", firstName, lastName);
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getFirstName().equalsIgnoreCase(firstName)
          && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        return personEntity;
      }
    }
    LOGGER.trace("Non-existent person ({}, {}).", firstName, lastName);
    return null;
  }

  /**
   * Search people by address
   * 
   * @param addresses Address search
   * @return List of PersonEntity
   */
  @Override
  public List<PersonEntity> findPersonByAddresses(List<String> addresses) {
    LOGGER.debug("Person search query by address list ({}).", addresses);
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

  /**
   * Search children by address
   * 
   * @param address Address search
   * @return List of PersonEntity
   */
  @Override
  public List<PersonEntity> findChildByAddress(String address) {
    LOGGER.debug("Person search query for children living at an address ({}).", address);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getAddress().equalsIgnoreCase(address)
          && personEntity.getAge() <= 18) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  /**
   * Finding other family members
   * 
   * @param firstName First name
   * @param lastName Last name
   * @param address Address search
   * @return List of PersonEntity
   */
  @Override
  public List<PersonEntity> findOtherHouseholdPersonsByNameAddress(String firstName, String lastName, String address) {
    LOGGER.debug("Person search query for other household members ({}, {}).", firstName, lastName, address);
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

  /**
   * Search people by city
   * 
   * @param city Address search
   * @return List of PersonEntity
   */
  @Override
  public List<PersonEntity> findPersonByCity(String city) {
    LOGGER.debug("Person search query by city ({}).", city);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getCity().equalsIgnoreCase(city)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  /**
   * Search for people with the same name
   * 
   * @param firstName First name
   * @param lastName Last name
   * @return List of PersonEntity
   */
  @Override
  public List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName) {
    LOGGER.debug("Person search query for people with the same name ({}, {}).", firstName, lastName);
    List<PersonEntity> returnList = new ArrayList<>();
    for (PersonEntity personEntity : personEntities) {
      if (personEntity.getLastName().equalsIgnoreCase(lastName)) {
        returnList.add(personEntity);
      }
    }
    return returnList;
  }

  /**
   * Add a person
   * 
   * @param newPersonEntity An object PersonEntity
   * @return PersonEntity, successful saved
   */
  @Override
  public PersonEntity save(PersonEntity newPersonEntity) {
    String newFirstName = newPersonEntity.getFirstName();
    String newLastName = newPersonEntity.getLastName();
    
    LOGGER.debug("Query add person ({}, {}).", newFirstName, newLastName);
    
    if (findPersonByName(newFirstName, newLastName) != null) {
      LOGGER.warn("Unable to add existing person ({}, {}).", newFirstName, newLastName);
      return null;
    }
    
    ++ personSequence;
    newPersonEntity.setId(personSequence);
    if (personEntities.add(newPersonEntity) == false) {
      LOGGER.error("Unable to add non-existent person ({}, {}).", newFirstName, newLastName);
      return null;
    }
    return newPersonEntity;
  }

  /**
   * Update an existing person
   * 
   * @param newPersonEntity An object new PersonEntity
   * @return PersonEntity, successful updated
   */
  @Override
  public PersonEntity update(PersonEntity newPersonEntity) {
    String firstName = newPersonEntity.getFirstName();
    String lastName = newPersonEntity.getLastName();
    
    LOGGER.debug("Query update person ({}, {}).", firstName, lastName);
    
    PersonEntity personEntity = findPersonByName(firstName, lastName);
    
    if (personEntity == null) {
      LOGGER.warn("Updated a non-existent person ({}, {}).", firstName, lastName);
      return null;
    }
    
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

  /**
   * Delete a person
   * 
   * @param firstName First name
   * @param lastName Last name
   * @return True, successful deleted
   */
  @Override
  public boolean delete(String firstName, String lastName) {
    LOGGER.debug("Query delete person ({}, {}).", firstName, lastName);
    
    PersonEntity personEntity = findPersonByName(firstName, lastName);
    
    if (personEntity == null) {
      LOGGER.warn("Removed a non-existent person ({}, {}).", firstName, lastName);
      return false;
    }
    
    if (personEntities.remove(personEntity) == false) {
      LOGGER.error("Unable to delete existing person ({}, {}).", firstName, lastName);
      return false;
    }
    return true;
  }
}
