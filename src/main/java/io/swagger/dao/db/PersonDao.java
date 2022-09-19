package io.swagger.dao.db;

import java.util.List;
import io.swagger.dao.db.entities.PersonEntity;

public interface PersonDao {
  void clearTable();
  PersonEntity findPersonByName(String firstName, String lastName);
  PersonEntity findPersonById(Integer Id);
  List<PersonEntity> findPersonByAddresses(List<String> addresses);
  List<PersonEntity> findChildByAddress(String address);
  List<PersonEntity> findOtherHouseholdPersonsByNameAddress(String firstName, String lastName, String address);
  List<PersonEntity> findPersonByCity(String city);
  List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName);
  PersonEntity save(PersonEntity personEntity);
  PersonEntity update(PersonEntity personEntity);
  void delete(String firstName, String lastName);
}
