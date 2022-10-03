package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.business.FireBusiness;
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

@SpringBootTest
class FireBusinessIT {
  
  @Autowired
  private FireBusiness fireBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private FireStationDao fireStationDao;
  
  private static PersonEntity mickPersonEntity;
  private static MedicalRecordEntity mickMedicalRecordEntity;
  private static MedicationEntity mickMedicationEntityAznol;
  private static AllergyEntity mickAllergyEntityNillacilan;
  private static FireStationEntity mickFireStationEntity;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickMedicalRecordEntity = MickBoydData.getMedicalRecordEntity();
    mickMedicationEntityAznol = MickBoydData.getMedicationEntityAznol();
    mickAllergyEntityNillacilan = MickBoydData.getAllergyEntityNillacilan();
    mickFireStationEntity = FireStationData.getFireStationEntityWallStreet();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingInAddress
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getPersonsLivingInAddress_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Person> result = fireBusiness.getPersonsLivingInAddress("1234 Wall Street");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getPersonsLivingInAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireBusiness.getPersonsLivingInAddress("1234 Wall Street")).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedicationByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getMedicationByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Medication> result = fireBusiness.getMedicationByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getMedicationAznol())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getMedicationByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireBusiness.getMedicationByName(1)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllergyByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAllergyByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Allergy> result = fireBusiness.getAllergyByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getAllergyNillacilan())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getAllergyByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireBusiness.getAllergyByName(1)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getFireStationByStationAddress
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getFireStationByStationAddress_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    List<FireStation> result = fireBusiness.getFireStationByStationAddress("1234 Wall Street");
    // THEN
    assertThat(result.contains(FireStationData.getFireStationWallStreet())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getFireStationByStationAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireBusiness.getFireStationByStationAddress("1234 Wall Street")).isEmpty();
    // THEN
  }
}
