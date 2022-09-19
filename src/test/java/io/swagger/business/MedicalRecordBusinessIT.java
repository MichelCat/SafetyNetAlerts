package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.data.MickBoydData;
import io.swagger.model.MedicalRecord;
import io.swagger.utils.DateUtils;
import io.swagger.utils.MedicalRecordUtils;
import io.swagger.dao.db.entities.AllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;

@SpringBootTest
public class MedicalRecordBusinessIT {
  
  @Autowired
  private MedicalRecordBusiness medicalRecordBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
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

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }

  // -----------------------------------------------------------------------------------------------
  // Method saveMedicalRecord
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void saveMedicalRecord_Normal() {
    // GIVEN
    MedicalRecord medicalRecord = MickBoydData.getMedicalRecord();
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    assertThat(medicalRecordBusiness.saveMedicalRecord(medicalRecord)).isEqualTo(medicalRecord);
    // THEN
    PersonEntity personEntity = personDao.findPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
    assertThat(personEntity.getBirthdate()).isEqualTo(dateUtils.stringDDMMYYYYToDateConversion(medicalRecord.getBirthdate()));
    
    MedicalRecordEntity medicalRecordEntity = medicalRecordDao.findMedicalRecordEntityById(personEntity.getId());
    MedicalRecord medicalRecordNew = medicalRecordUtils.medicalRecordEntityToMedicalRecord(medicalRecordEntity,personEntity);
    assertThat(medicalRecord).isEqualTo(medicalRecordNew);
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateMedicalRecord
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void updateMedicalRecord_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    
    var allergyEntity = new AllergyEntity();
    allergyEntity.setAllergy("peanut");
    allergyEntity = allergyDao.save(allergyEntity);
    
    var medicationEntity = new MedicationEntity();
    medicationEntity.setMedication("tetracyclaz");
    medicationEntity = medicationDao.save(medicationEntity);
    
    MedicalRecord medicalRecordNew = MickBoydData.getUpdateMedicalRecord();
    // WHEN
    MedicalRecord result = medicalRecordBusiness.updateMedicalRecord(medicalRecordNew);
    // THEN
    PersonEntity personEntity = personDao.findPersonByName(medicalRecordNew.getFirstName(), medicalRecordNew.getLastName());
    assertThat(personEntity.getBirthdate()).isEqualTo(dateUtils.stringDDMMYYYYToDateConversion(medicalRecordNew.getBirthdate()));
    
    assertThat(result).isEqualTo(medicalRecordNew);
  }

  // -----------------------------------------------------------------------------------------------
  // Method deleteMedicalRecord
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void deleteFireStation_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    medicalRecordBusiness.deleteMedicalRecord("Mick", "Boyd");
    // THEN
    PersonEntity personEntity = personDao.findPersonByName("Mick", "Boyd");
    assertThat(medicalRecordDao.findMedicalRecordEntityById(personEntity.getId())).isNull();
  }
}
