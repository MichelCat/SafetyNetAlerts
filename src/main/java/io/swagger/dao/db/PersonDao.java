package io.swagger.dao.db;

import java.util.List;
import java.util.SortedSet;
import io.swagger.dao.db.entities.PersonEntity;

public interface PersonDao {
  SortedSet<PersonEntity> getPersonEntities();
  PersonEntity findPersonByName(String firstName, String lastName);
  List<PersonEntity> findPersonByAddresses(List<String> addresses);
  List<PersonEntity> findChildByAddress(String address);
  List<PersonEntity> findOtherHouseholdPersonsByName(String firstName, String lastName, String address);
  List<PersonEntity> findPersonByCity(String city);
  List<PersonEntity> findAllPersonsWithTheSameName(String firstName, String lastName);
  PersonEntity save(PersonEntity personEntity);
  PersonEntity update(PersonEntity personEntity);
}
