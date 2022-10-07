package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;

/**
 * PersonDaoIT is the integration test class managing the PersonEntity list
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class PersonDaoIT {

  @Autowired
  private PersonDao personDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  
  private static PersonEntity mickPersonEntity;
  private static PersonEntity youngPersonEntity;
  private static String mickFirstName;
  private static String mickLastName;
  private static String mickAddress;

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickFirstName = mickPersonEntity.getFirstName();
    mickLastName = mickPersonEntity.getLastName();
    mickAddress = mickPersonEntity.getAddress();
    youngPersonEntity = YoungBoydData.getPersonEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method clearTable
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Clear PersonEntity list
   */
  @Test
  void clearTable_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    personDao.clearTable();
    // THEN
    assertThat(personDao.findPersonByName(mickFirstName, mickLastName)).isNull();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findPersonByName
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search person by first name and last name
   */
  @Test
  void findPersonByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    PersonEntity result = personDao.findPersonByName(mickFirstName, mickLastName);
    // THEN
    assertThat(result).isEqualTo(mickPersonEntity);
  }
  
  /**
   * Borderline case test, empty list, Search person by first name and last name
   */
  @Test
  void findPersonByName_EmptyList() {
    // GIVEN
    // WHEN
    PersonEntity result = personDao.findPersonByName(mickFirstName, mickLastName);
    // THEN
    assertThat(result).isNull();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findPersonByAddresses
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search people by address
   */
  @Test
  void findPersonByAddresses_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    List<String> addresses = new ArrayList<>();
    addresses.add(mickAddress);
    // WHEN
    List<PersonEntity> result = personDao.findPersonByAddresses(addresses);
    // THEN
    assertThat(result.contains(mickPersonEntity)).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Search people by address
   */
  @Test
  void findPersonByAddresses_EmptyList() {
    // GIVEN
    List<String> addresses = new ArrayList<>();
    addresses.add(mickAddress);
    // WHEN
    assertThat(personDao.findPersonByAddresses(addresses)).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method findChildByAddress
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search children by address
   */
  @Test
  void findChildByAddress_Normal() {
    // GIVEN
    personDao.save(youngPersonEntity);
    // WHEN
    List<PersonEntity> result = personDao.findChildByAddress(mickAddress);
    // THEN
    assertThat(result.contains(youngPersonEntity)).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Search children by address
   */
  @Test
  void findChildByAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.findChildByAddress(mickAddress)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findOtherHouseholdPersonsByNameAddress
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Finding other family members
   */
  @Test
  void findOtherHouseholdPersonsByNameAddress_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    personDao.save(youngPersonEntity);
    // WHEN
    List<PersonEntity> result = personDao.findOtherHouseholdPersonsByNameAddress(mickFirstName, mickLastName, mickAddress);
    // THEN
    assertThat(result.contains(youngPersonEntity)).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Finding other family members
   */
  @Test
  void findOtherHouseholdPersonsByNameAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.findOtherHouseholdPersonsByNameAddress(mickFirstName, mickLastName, mickAddress)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findPersonByCity
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search people by city
   */
  @Test
  void findPersonByCity_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    personDao.save(youngPersonEntity);
    // WHEN
    List<PersonEntity> result = personDao.findPersonByCity(mickPersonEntity.getCity());
    // THEN
    assertThat(result.contains(youngPersonEntity)).isTrue();
    assertThat(result.contains(mickPersonEntity)).isTrue();
    assertThat(result.size()).isEqualTo(2);
  }
  
  /**
   * Borderline case test, empty list, Search people by city
   */
  @Test
  void findPersonByCity_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.findPersonByCity(mickPersonEntity.getCity())).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findAllPersonsWithTheSameName
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search for people with the same name
   */
  @Test
  void findAllPersonsWithTheSameName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    personDao.save(youngPersonEntity);
    // WHEN
    List<PersonEntity> result = personDao.findAllPersonsWithTheSameName(mickFirstName, mickLastName);
    // THEN
    assertThat(result.contains(youngPersonEntity)).isTrue();
    assertThat(result.contains(mickPersonEntity)).isTrue();
    assertThat(result.size()).isEqualTo(2);
  }
  
  /**
   * Borderline case test, empty list, Search for people with the same name
   */
  @Test
  void findAllPersonsWithTheSameName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.findAllPersonsWithTheSameName(mickFirstName, mickLastName)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method save
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a person
   */
  @Test
  void save_Normal() {
    // GIVEN
    // WHEN
    PersonEntity result = personDao.save(mickPersonEntity);
    // THEN
    assertThat(result).isEqualTo(mickPersonEntity);
    assertThat(result).isEqualTo(personDao.findPersonByName(mickFirstName, mickLastName));
  }
  
  /**
   * Borderline case test, record already created, Add a person
   */
  @Test
  void save_recordingPresent() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    assertThat(personDao.save(mickPersonEntity)).isNull();
    // THEN
    assertThat(mickPersonEntity).isEqualTo(personDao.findPersonByName(mickFirstName, mickLastName));
  }

  // -----------------------------------------------------------------------------------------------
  // Method update
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Update an existing person
   */
  @Test
  void update_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    
    var newPersonEntity = mickPersonEntity;
    newPersonEntity.setAddress("5678 Wall Street");
    // WHEN
    PersonEntity result = personDao.update(newPersonEntity);
    // THEN
    assertThat(result).isEqualTo(newPersonEntity);
    assertThat(result).isEqualTo(personDao.findPersonByName(mickFirstName, mickLastName));
  }
  
  /**
   * Borderline case test, empty list, Update an existing person
   */
  @Test
  void update_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.update(mickPersonEntity)).isNull();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method delete
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Delete a person
   */
  @Test
  void delete_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    assertThat(personDao.delete(mickFirstName, mickLastName)).isTrue();
    // THEN
    PersonEntity readPersonEntity = personDao.findPersonByName(mickFirstName, mickLastName);
    assertThat(readPersonEntity).isNull();
  }
  
  /**
   * Borderline case test, empty list, Delete a person
   */
  @Test
  void delete_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personDao.delete(mickFirstName, mickLastName)).isFalse();
    // THEN
    PersonEntity readPersonEntity = personDao.findPersonByName(mickFirstName, mickLastName);
    assertThat(readPersonEntity).isNull();
  }
}
