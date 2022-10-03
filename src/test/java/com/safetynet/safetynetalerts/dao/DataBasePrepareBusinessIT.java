package com.safetynet.safetynetalerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;

@SpringBootTest
class DataBasePrepareBusinessIT {
  
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareBusiness;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private PersonDao personDao;
  
  // -----------------------------------------------------------------------------------------------
  // Method clearDataBase
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void clearDataBase_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    dataBasePrepareBusiness.clearDataBase();
    // THEN
    assertThat(personDao.findPersonByName("Mick", "Boyd")).isNull();
    assertThat(allergyDao.findIdAllergyByName("nillacilan")).isNull();
    assertThat(medicationDao.findIdMedicationByName("aznol")).isNull();
    assertThat(medicalRecordDao.findMedicalRecordEntityById(1)).isNull();
    assertThat(fireStationDao.fireStationByStationNumberStationAddress(1, "1234 Wall Street")).isNull();
  }
}
