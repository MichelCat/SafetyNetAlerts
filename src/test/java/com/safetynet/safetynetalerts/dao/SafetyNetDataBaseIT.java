package com.safetynet.safetynetalerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;

/**
 * SafetyNetDataBaseIT is the class of integration tests on the Event Listener component to load the database from a JSON file
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class SafetyNetDataBaseIT {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private PersonDao personDao;
  
  // -----------------------------------------------------------------------------------------------
  // Method contextRefreshedEvent
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, ContextRefreshedEvent for loading the database from a JSON file
   */
  @Test
  void contextRefreshedEvent_Normal() {
    // GIVEN
    // WHEN
    safetyNetDataBase.contextRefreshedEvent();
    // THEN
    PersonEntity personEntity = personDao.findPersonByName("John", "Boyd");
    assertThat(personEntity).isNotNull();
    assertThat(medicalRecordDao.findAllergyEntityById(personEntity.getId())).isNotEmpty();
    assertThat(medicalRecordDao.findMedicationEntityById(personEntity.getId())).isNotEmpty();
    assertThat(medicalRecordDao.findMedicalRecordEntityById(personEntity.getId())).isNotNull();
    assertThat(fireStationDao.fireStationAddressByStationNumber(1)).isNotEmpty();
  }
}
