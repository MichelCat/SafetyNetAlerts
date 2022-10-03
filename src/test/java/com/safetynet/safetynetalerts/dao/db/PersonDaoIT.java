package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;

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
  // General case
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
  // General case
  @Test
  void findPersonByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    PersonEntity result = personDao.findPersonByName(mickFirstName, mickLastName);
    // THEN
    assertThat(result).isEqualTo(mickPersonEntity);
  }
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
  @Test
  void save_Normal() {
    // GIVEN
    // WHEN
    PersonEntity result = personDao.save(mickPersonEntity);
    // THEN
    assertThat(result).isEqualTo(mickPersonEntity);
    assertThat(result).isEqualTo(personDao.findPersonByName(mickFirstName, mickLastName));
  }
  
  // Borderline cases : Record already created
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
  // General case
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
  
  // Borderline cases : Empty list
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
  // General case
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
  
  // Borderline cases : Empty list
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
