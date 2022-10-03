package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;

@SpringBootTest
class MedicationDaoIT {

  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  
  private static MedicationEntity mickMedicationEntityAznol;

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBase();
    mickMedicationEntityAznol = MickBoydData.getMedicationEntityAznol();
  }

  // -----------------------------------------------------------------------------------------------
  // Method clearTable
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void clearTable_Normal() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    // WHEN
    medicationDao.clearTable();
    // THEN
    assertThat(medicationDao.medicationById(mickMedicationEntityAznol.getId())).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method medicationById
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void medicationById_Normal() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    // WHEN
    assertThat(medicationDao.medicationById(mickMedicationEntityAznol.getId())).isEqualTo(mickMedicationEntityAznol);
    // THEN
  }
  
  // Borderline cases : Empty list
  @Test
  void medicationById_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicationDao.medicationById(mickMedicationEntityAznol.getId())).isNull();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method findIdMedicationByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void findIdMedicationByName_Normal() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    // WHEN
    assertThat(medicationDao.findIdMedicationByName(mickMedicationEntityAznol.getMedication())).isEqualTo(mickMedicationEntityAznol);
    // THEN
  }
  
  // Borderline cases : Empty list
  @Test
  void findIdMedicationByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(medicationDao.findIdMedicationByName(mickMedicationEntityAznol.getMedication())).isNull();
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
    MedicationEntity result = medicationDao.save(mickMedicationEntityAznol);
    // THEN
    assertThat(result).isEqualTo(mickMedicationEntityAznol);
    assertThat(result).isEqualTo(medicationDao.medicationById(result.getId()));
  }
  
  // Borderline cases : Record already created
  @Test
  void save_recordingPresent() {
    // GIVEN
    medicationDao.save(mickMedicationEntityAznol);
    // WHEN
    assertThat(medicationDao.save(mickMedicationEntityAznol)).isNull();
    // THEN
    assertThat(mickMedicationEntityAznol).isEqualTo(medicationDao.medicationById(mickMedicationEntityAznol.getId()));
  }
}
