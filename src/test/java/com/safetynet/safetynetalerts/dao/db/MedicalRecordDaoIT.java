package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;

@SpringBootTest
class MedicalRecordDaoIT {

  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  
  private static MedicalRecordEntity mickMedicalRecordEntity;
  private static Integer mickIdPerson;

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBase();
    mickMedicalRecordEntity = MickBoydData.getMedicalRecordEntity();
    mickIdPerson = mickMedicalRecordEntity.getIdPerson();
  }

  // -----------------------------------------------------------------------------------------------
  // Method clearTable
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void clearTable_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    medicalRecordDao.clearTable();
    // THEN
    assertThat(medicalRecordDao.findMedicalRecordEntityById(mickIdPerson)).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method findAllergyEntityById
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void findAllergyEntityById_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<MedicalRecordAllergyEntity> result = medicalRecordDao.findAllergyEntityById(mickIdPerson);
    // THEN
    assertThat(result).isEqualTo(mickMedicalRecordEntity.getAllergies());
  }
  
  // Borderline cases : Empty list
  @Test
  void findAllergyEntityById_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicalRecordDao.findAllergyEntityById(mickIdPerson)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findMedicationEntityById
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void findMedicationEntityById_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<MedicalRecordMedicationEntity> result = medicalRecordDao.findMedicationEntityById(mickIdPerson);
    // THEN
    assertThat(result).isEqualTo(mickMedicalRecordEntity.getMedications());
  }
  
  // Borderline cases : Empty list
  @Test
  void findMedicationEntityById_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicalRecordDao.findMedicationEntityById(mickIdPerson)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method findMedicalRecordEntityById
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void findMedicalRecordEntityById_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    MedicalRecordEntity result = medicalRecordDao.findMedicalRecordEntityById(mickIdPerson);
    // THEN
    assertThat(result).isEqualTo(mickMedicalRecordEntity);
  }
  
  // Borderline cases : Empty list
  @Test
  void findMedicalRecordEntityById_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicalRecordDao.findMedicalRecordEntityById(mickIdPerson)).isNull();
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
    MedicalRecordEntity result = medicalRecordDao.save(mickMedicalRecordEntity);
    // THEN
    assertThat(result).isEqualTo(mickMedicalRecordEntity);
    assertThat(result).isEqualTo(medicalRecordDao.findMedicalRecordEntityById(mickIdPerson));
  }
  
  // Borderline cases : Record already created
  @Test
  void save_recordingPresent() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    assertThat(medicalRecordDao.save(mickMedicalRecordEntity)).isNull();
    // THEN
    assertThat(mickMedicalRecordEntity).isEqualTo(medicalRecordDao.findMedicalRecordEntityById(mickIdPerson));
  }

  // -----------------------------------------------------------------------------------------------
  // Method update
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void update_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    
    var newMedicalRecordEntity = mickMedicalRecordEntity;
    newMedicalRecordEntity.setAllergies(null);
    newMedicalRecordEntity.setMedications(null);
    // WHEN
    MedicalRecordEntity result = medicalRecordDao.update(newMedicalRecordEntity);
    // THEN
    assertThat(result).isEqualTo(newMedicalRecordEntity);
    
    MedicalRecordEntity readMedicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(mickIdPerson);
    assertThat(result).isEqualTo(readMedicalRecordEntity);
  }
  
  // Borderline cases : Empty list
  @Test
  void update_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicalRecordDao.update(mickMedicalRecordEntity)).isNull();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method delete
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void delete_Normal() {
    // GIVEN
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    assertThat(medicalRecordDao.delete(mickIdPerson)).isTrue();
    // THEN
    MedicalRecordEntity readMedicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(mickIdPerson);
    assertThat(readMedicalRecordEntity).isNull();
  }
  
  // Borderline cases : Empty list
  @Test
  void delete_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicalRecordDao.delete(mickIdPerson)).isFalse();
    // THEN
    MedicalRecordEntity readMedicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(mickIdPerson);
    assertThat(readMedicalRecordEntity).isNull();
  }
}
