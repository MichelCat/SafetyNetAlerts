package com.safetynet.safetynetalerts.dao;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.dao.json.entities.FireStationJson;
import com.safetynet.safetynetalerts.dao.json.entities.MedicalRecordJson;
import com.safetynet.safetynetalerts.dao.json.entities.PersonJson;
import com.safetynet.safetynetalerts.dao.json.entities.SafetyNetJson;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;

@SpringBootTest
class LoadJsonFileInDatabaseBusinessIT {
  
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
    assertThat(personDao.findPersonByName("Mick", "Boyd")).isEqualTo(MickBoydData.getPersonEntity());
    assertThat(allergyDao.findIdAllergyByName("nillacilan")).isEqualTo(MickBoydData.getAllergyEntityNillacilan());
    assertThat(medicationDao.findIdMedicationByName("aznol")).isEqualTo(MickBoydData.getMedicationEntityAznol());
    assertThat(medicalRecordDao.findMedicalRecordEntityById(1)).isEqualTo(MickBoydData.getMedicalRecordEntity());
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(1, "1234 Wall Street");
    assertThat(readFireStationEntity).isEqualTo(FireStationData.getFireStationEntityWallStreet());
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method readFileJson
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void readFileJson_Normal() throws StreamReadException, DatabindException, IOException {
    // GIVEN
    // WHEN
    SafetyNetJson result = loadJsonFileInDatabaseService.readFileJson("FireStation.json");
    // THEN
    FireStationJson fireStationJson = new FireStationJson();
    fireStationJson.setAddress("1234 Wall Street");
    fireStationJson.setStation("1");
    safetyNetJson.addFirestationsItem(fireStationJson);
    
    assertThat(result).isEqualTo(safetyNetJson);
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
    safetyNetJson.addPersonsItem(personJson);
    // WHEN
    loadJsonFileInDatabaseService.setListPersonEntity(safetyNetJson);
    // THEN
    PersonEntity mickPersonEntity = MickBoydData.getPersonEntity();
    mickPersonEntity.setBirthdate(null);
    
    PersonEntity readPerson = personDao.findPersonByName("Mick", "Boyd");
    assertThat(readPerson).isEqualTo(mickPersonEntity);
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
    safetyNetJson.addFirestationsItem(fireStationJson);
    // WHEN
    loadJsonFileInDatabaseService.setListFireStationEntity(safetyNetJson);
    // THEN
    FireStationEntity readFireStation = fireStationDao.fireStationByStationNumberStationAddress(1, "1234 Wall Street");
    assertThat(readFireStation).isEqualTo(FireStationData.getFireStationEntityWallStreet());
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method setListAllergyEntity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void setListAllergyEntity_Normal() {
    // GIVEN
    MedicalRecordJson medicalRecordJson = new MedicalRecordJson();
    medicalRecordJson.addAllergiesItem("nillacilan");
    safetyNetJson.addMedicalrecordsItem(medicalRecordJson);
    // WHEN
    loadJsonFileInDatabaseService.setListAllergyEntity(safetyNetJson);
    // THEN
    AllergyEntity readAllergy = allergyDao.findIdAllergyByName("nillacilan");
    assertThat(readAllergy).isEqualTo(MickBoydData.getAllergyEntityNillacilan());
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method setListMedicationEntity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void setListMedicationEntity_Normal() {
    // GIVEN
    MedicalRecordJson medicalRecordJson = new MedicalRecordJson();
    medicalRecordJson.addMedicationsItem("aznol:350mg");
    safetyNetJson.addMedicalrecordsItem(medicalRecordJson);
    // WHEN
    loadJsonFileInDatabaseService.setListMedicationEntity(safetyNetJson);
    // THEN
    MedicationEntity readMedication = medicationDao.findIdMedicationByName("aznol");
    assertThat(readMedication).isEqualTo(MickBoydData.getMedicationEntityAznol());
  }

  // Medicament without dosage
  @Test
  void setListMedicationEntity_withoutDosage() {
    // GIVEN
    MedicalRecordJson medicalRecordJson = new MedicalRecordJson();
    medicalRecordJson.addMedicationsItem("aznol");
    safetyNetJson.addMedicalrecordsItem(medicalRecordJson);
    // WHEN
    loadJsonFileInDatabaseService.setListMedicationEntity(safetyNetJson);
    // THEN
    assertThat(medicationDao.findIdMedicationByName("aznol")).isNull();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method setListMedicalRecordEntity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void setListMedicalRecordEntity_Normal() {
    // GIVEN
    personDao.save(MickBoydData.getPersonEntity());
    allergyDao.save(MickBoydData.getAllergyEntityNillacilan());
    medicationDao.save(MickBoydData.getMedicationEntityAznol());
    
    MedicalRecordJson medicalRecordJson = new MedicalRecordJson();
    medicalRecordJson.setFirstName("Mick");
    medicalRecordJson.setLastName("Boyd");
    medicalRecordJson.setBirthdate("03/06/1984");
    medicalRecordJson.addAllergiesItem("nillacilan");
    medicalRecordJson.addMedicationsItem("aznol:350mg");
    safetyNetJson.addMedicalrecordsItem(medicalRecordJson);
    // WHEN
    loadJsonFileInDatabaseService.setListMedicalRecordEntity(safetyNetJson);
    // THEN
    PersonEntity readPerson = personDao.findPersonByName("Mick", "Boyd");
    assertThat(readPerson.getBirthdate()).isEqualTo(new Date("1984/06/03"));
    
    MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
    medicalRecordEntity.setIdPerson(readPerson.getId());
    
    MedicalRecordAllergyEntity medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
    medicalRecordAllergyEntity.setIdAlergy(allergyDao.findIdAllergyByName("nillacilan").getId());
    medicalRecordEntity.addMedicalRecordAllergiesItem(medicalRecordAllergyEntity);
    
    MedicalRecordMedicationEntity medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
    medicalRecordMedicationEntity.setIdMedication( medicationDao.findIdMedicationByName("aznol").getId());
    medicalRecordMedicationEntity.setDosage("350mg");
    medicalRecordEntity.addMedicalRecordMedicationsItem(medicalRecordMedicationEntity);
    
    MedicalRecordEntity readMedicalRecord = medicalRecordDao.findMedicalRecordEntityById(readPerson.getId());
    assertThat(readMedicalRecord).isEqualTo(medicalRecordEntity);
  }
}
