package io.swagger.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;

@SpringBootTest
public class DataBasePrepareBusinessIT {
  
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
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(1);
    assertThat(stationAddresses.contains("1234 Wall Street")).isFalse();
  }
}
