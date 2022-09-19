package io.swagger.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.dao.json.entities.FireStationJson;
import io.swagger.dao.json.entities.PersonJson;
import io.swagger.dao.json.entities.SafetyNetJson;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@SpringBootTest
public class LoadJsonFileInDatabaseBusinessIT {
  
  private static SafetyNetJson safetyNetJson;

  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
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

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    safetyNetJson = new SafetyNetJson();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method loadDataBase
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void loadDataBase_Normal() {
    // GIVEN
    // WHEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // THEN
    assertThat(personDao.findPersonByName("Mick", "Boyd")).isNotNull();
    assertThat(allergyDao.findIdAllergyByName("nillacilan")).isNotNull();
    assertThat(medicationDao.findIdMedicationByName("aznol")).isNotNull();
    assertThat(medicalRecordDao.findMedicalRecordEntityById(1)).isNotNull();
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(1);
    assertThat(stationAddresses.contains("1234 Wall Street")).isTrue();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method setListPersonEntity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void setListPersonEntity_Normal() {
    // GIVEN
    PersonJson personJson = new PersonJson();
    personJson.setFirstName("Mick");
    personJson.setLastName("Boyd");
    personJson.setAddress("1234 Wall Street");
    personJson.setCity("Culver");
    personJson.setZip("97451");
    personJson.setPhone("841-874-6512");
    personJson.setEmail("miboyd@email.com");
    List<PersonJson> persons = new ArrayList<>();
    persons.add(personJson);
    safetyNetJson.setPersons(persons);
    // WHEN
    loadJsonFileInDatabaseService.setListPersonEntity(safetyNetJson);
    // THEN
    PersonEntity personEntity = personDao.findPersonByName("Mick", "Boyd");
    assertThat(personEntity.getFirstName()).isEqualTo("Mick");
    assertThat(personEntity.getLastName()).isEqualTo("Boyd");
    assertThat(personEntity.getAddress()).isEqualTo("1234 Wall Street");
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method setListFireStationEntity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void setListFireStationEntity_Normal() {
    // GIVEN
    FireStationJson fireStationJson = new FireStationJson();
    fireStationJson.setAddress("1234 Wall Street");
    fireStationJson.setStation("1");
    List<FireStationJson> firestations = new ArrayList<>();
    firestations.add(fireStationJson);
    safetyNetJson.setFirestations(firestations);
    // WHEN
    loadJsonFileInDatabaseService.setListFireStationEntity(safetyNetJson);
    // THEN
  }
  
  
}
