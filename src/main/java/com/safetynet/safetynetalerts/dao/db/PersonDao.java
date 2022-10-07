package com.safetynet.safetynetalerts.dao.db;

import java.util.List;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;

/**
 * PersonDao is interface that manages the PersonEntity list
 * 
 * @author MC
 * @version 1.0
 */
public interface PersonDao {
  void clearTable();
  PersonEntity findPersonByName(String firstName, String lastName);
  List<PersonEntity> findPersonByAddresses(List<String> addresses);
  List<PersonEntity> findChildByAddress(String address);
  List<PersonEntity> findOtherHouseholdPersonsByNameAddress(String firstName, String lastName, String address);
  List<PersonEntity> findPersonByCity(String city);
  List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName);
  PersonEntity save(PersonEntity personEntity);
  PersonEntity update(PersonEntity personEntity);
  boolean delete(String firstName, String lastName);
}
