package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

/**
 * FloodBusinessIT is a class of integration tests on households served by the fire station.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class FloodBusinessIT {
  
  @Autowired
  private FloodBusiness floodBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private AllergyDao allergyDao;

  private static PersonEntity mickPersonEntity;
  private static FireStationEntity mickFireStationEntity;
  private static MedicalRecordEntity mickMedicalRecordEntity;
  private static MedicationEntity mickMedicationEntityAznol;
  private static AllergyEntity mickAllergyEntityNillacilan;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickFireStationEntity = FireStationData.getFireStationEntityWallStreet();
    mickMedicalRecordEntity = MickBoydData.getMedicalRecordEntity();
    mickMedicationEntityAznol = MickBoydData.getMedicationEntityAznol();
    mickAllergyEntityNillacilan = MickBoydData.getAllergyEntityNillacilan();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get a list of all households served by the station
   */
  @Test
  void getPersonsLivingNearStation_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    personDao.save(mickPersonEntity);
    // WHEN
    List<Person> result = floodBusiness.getPersonsLivingNearStation("1");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get a list of all households served by the station
   */
  @Test
  void getPersonsLivingNearStation_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(floodBusiness.getPersonsLivingNearStation("1")).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedicationByName
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get medications by person ID
   */
  @Test
  void getMedicationByName_Normal() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Medication> result = floodBusiness.getMedicationByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getMedicationAznol())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get medications by person ID
   */
  @Test
  void getMedicationByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(floodBusiness.getMedicationByName(1)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllergyByName
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get allergies by person ID
   */
  @Test
  void getAllergyByName_Normal() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Allergy> result = floodBusiness.getAllergyByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getAllergyNillacilan())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get allergies by person ID
   */
  @Test
  void getAllergyByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(floodBusiness.getAllergyByName(1)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getFireStationByStationAddress
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get fire stations by address
   */
  @Test
  void getFireStationByStationAddress_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    List<FireStation> result = floodBusiness.getFireStationByStationAddress("1234 Wall Street");
    // THEN
    assertThat(result.contains(FireStationData.getFireStationWallStreet())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get fire stations by address
   */
  @Test
  void getFireStationByStationAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(floodBusiness.getFireStationByStationAddress("1234 Wall Street")).isEmpty();
    // THEN
  }
}
