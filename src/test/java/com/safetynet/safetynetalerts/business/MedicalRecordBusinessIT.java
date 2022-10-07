package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.utils.DateUtils;
import com.safetynet.safetynetalerts.utils.MedicalRecordUtils;

/**
 * MedicalRecordBusinessIT is a class of integration tests on medical records.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class MedicalRecordBusinessIT {
  
  @Autowired
  private MedicalRecordBusiness medicalRecordBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private DateUtils dateUtils;
  @Autowired
  private MedicalRecordUtils medicalRecordUtils;
  
  private static PersonEntity mickPersonEntity;
  private static MedicalRecordEntity mickMedicalRecordEntity;
  private static MedicationEntity mickMedicationEntityAznol;
  private static AllergyEntity mickAllergyEntityNillacilan;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickMedicalRecordEntity = MickBoydData.getMedicalRecordEntity();
    mickMedicationEntityAznol = MickBoydData.getMedicationEntityAznol();
    mickAllergyEntityNillacilan = MickBoydData.getAllergyEntityNillacilan();
}

  // -----------------------------------------------------------------------------------------------
  // Method saveMedicalRecord
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a new medical record
   */
  @Test
  void saveMedicalRecord_Normal() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    medicationDao.save(mickMedicationEntityAznol);
    personDao.save(mickPersonEntity);
    // WHEN
    MedicalRecord result = medicalRecordBusiness.saveMedicalRecord(MickBoydData.getMedicalRecord());
    // THEN
    assertThat(result).isEqualTo(MickBoydData.getMedicalRecord());
    
    PersonEntity readPersonEntity = personDao.findPersonByName(result.getFirstName(), result.getLastName());
    assertThat(readPersonEntity.getBirthdate()).isEqualTo(dateUtils.stringDDMMYYYYToDateConversion(result.getBirthdate()));
    
    MedicalRecordEntity readMedicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(readPersonEntity.getId());
    MedicalRecord medicalRecordNew = medicalRecordUtils.medicalRecordEntityToMedicalRecord(readMedicalRecordEntity, readPersonEntity);
    assertThat(result).isEqualTo(medicalRecordNew);
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateMedicalRecord
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Update an existing medical record
   */
  @Test
  void updateMedicalRecord_Normal() {
    // GIVEN
    allergyDao.save(mickAllergyEntityNillacilan);
    allergyDao.save(MickBoydData.getAllergyEntityPeanut());
    
    medicationDao.save(mickMedicationEntityAznol);
    medicationDao.save(MickBoydData.getMedicationEntityTetracyclaz());
    
    personDao.save(mickPersonEntity);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    MedicalRecord result = medicalRecordBusiness.updateMedicalRecord(MickBoydData.getUpdateMedicalRecord());
    // THEN
    assertThat(result).isEqualTo(MickBoydData.getUpdateMedicalRecord());

    PersonEntity readPersonEntity = personDao.findPersonByName(result.getFirstName(), result.getLastName());
    assertThat(readPersonEntity.getBirthdate()).isEqualTo(dateUtils.stringDDMMYYYYToDateConversion(result.getBirthdate()));
    
    MedicalRecordEntity readMedicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(readPersonEntity.getId());
    MedicalRecord medicalRecordNew = medicalRecordUtils.medicalRecordEntityToMedicalRecord(readMedicalRecordEntity, readPersonEntity);
    assertThat(result).isEqualTo(medicalRecordNew);
  }

  // -----------------------------------------------------------------------------------------------
  // Method deleteMedicalRecord
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Delete an medical record
   */
  @Test
  void deleteFireStation_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    assertThat(medicalRecordBusiness.deleteMedicalRecord("Mick", "Boyd")).isTrue();
    // THEN
    PersonEntity readPersonEntity = personDao.findPersonByName("Mick", "Boyd");
    assertThat(medicalRecordDao.findMedicalRecordEntityById(readPersonEntity.getId())).isNull();
  }
}
