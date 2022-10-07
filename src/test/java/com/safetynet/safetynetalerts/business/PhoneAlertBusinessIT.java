package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Person;

/**
 * PhoneAlertBusinessIT is a class of integration tests on phone number to alert.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class PhoneAlertBusinessIT {
  
  @Autowired
  private PhoneAlertBusiness phoneAlertBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;

  private static PersonEntity mickPersonEntity;
  private static FireStationEntity mickFireStationEntity;
  
  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickFireStationEntity = FireStationData.getFireStationEntityWallStreet();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get the phone numbers of residents served by the fire station
   */
  @Test
  void getPersonsLivingNearStation_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    personDao.save(mickPersonEntity);
    // WHEN
    List<Person> result = phoneAlertBusiness.getPersonsLivingNearStation("1");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get the phone numbers of residents served by the fire station
   */
  @Test
  void getPersonsLivingNearStationn_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(phoneAlertBusiness.getPersonsLivingNearStation("1")).isEmpty();
    // THEN
  }
}
