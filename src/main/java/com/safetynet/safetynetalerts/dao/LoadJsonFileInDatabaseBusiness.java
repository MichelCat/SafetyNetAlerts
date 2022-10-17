package com.safetynet.safetynetalerts.dao;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.safetynet.safetynetalerts.dao.json.entities.FireStationJson;
import com.safetynet.safetynetalerts.dao.json.entities.MedicalRecordJson;
import com.safetynet.safetynetalerts.dao.json.entities.PersonJson;
import com.safetynet.safetynetalerts.dao.json.entities.SafetyNetJson;
import com.safetynet.safetynetalerts.utils.AllergyUtils;
import com.safetynet.safetynetalerts.utils.DateUtils;
import com.safetynet.safetynetalerts.utils.MedicationUtils;

/**
 * LoadJsonFileInDatabaseBusiness is the service for loading the database from a JSON file
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class LoadJsonFileInDatabaseBusiness {

  private static final Logger LOGGER = LoggerFactory.getLogger(LoadJsonFileInDatabaseBusiness.class);

  @Autowired
  private DateUtils dateUtils;
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
  @Autowired
  private AllergyUtils allergyUtils;
  @Autowired
  private MedicationUtils medicationUtils;

  /**
   * Loading JSON file into database
   * 
   * @param resourceLocation Resource location
   * @return True, if successful JSON file upload, and false if not.
   */
  public boolean loadDataBase(String resourceLocation) {
    SafetyNetJson safetyNetJson;
    try {
      safetyNetJson = readFileJson(resourceLocation);
      setListPersonEntity(safetyNetJson);
      setListFireStationEntity(safetyNetJson);
      setListAllergyEntity(safetyNetJson);
      setListMedicationEntity(safetyNetJson);
      setListMedicalRecordEntity(safetyNetJson);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Loading JSON file into SafetyNetJson object
   * 
   * @param resourceLocation Resource location
   * @return SafetyNetJson, if successfully loaded JSON file into object, and null if not.
   * @throws IOException, if Unknown JSON file or JSON file deserialization error
   */
  public SafetyNetJson readFileJson(String resourceLocation) throws IOException {
    File dataJson;
    SafetyNetJson safetyNetJson;
    try {
      dataJson = ResourceUtils.getFile("classpath:" + resourceLocation);
    } catch (Exception e) {
      LOGGER.error("Unknown JSON file ({}).", resourceLocation);
      throw e;
    }
    try {
      var objectMapper = new ObjectMapper();
      safetyNetJson = objectMapper.readValue(dataJson, SafetyNetJson.class);
    } catch (Exception e) {
      LOGGER.error("JSON file deserialization error ({}).", resourceLocation);
      throw e;
    }
    return safetyNetJson;
  }

  /**
   * Loading PersonEntity table
   * 
   * @param safetyNetJson SafetyNetJson object
   */
  public void setListPersonEntity(SafetyNetJson safetyNetJson) {
    for (PersonJson personJson : CollectionUtils.emptyIfNull(safetyNetJson.getPersons())) {
      var personEntity = new PersonEntity();
      personEntity.setFirstName(personJson.getFirstName());
      personEntity.setLastName(personJson.getLastName());
      personEntity.setAddress(personJson.getAddress());
      personEntity.setPhoneNumber(personJson.getPhone());
      personEntity.setZip(personJson.getZip());
      personEntity.setCity(personJson.getCity());
      personEntity.setEmail(personJson.getEmail());
      if (personDao.save(personEntity) == null) {
        LOGGER.warn("Duplicate person in JSON file ({}, {}).", personJson.getFirstName(), personJson.getLastName());
      }
    }
  }

  /**
   * Loading FireStationEntity table
   * 
   * @param safetyNetJson SafetyNetJson object
   */
  public void setListFireStationEntity(SafetyNetJson safetyNetJson) {
    for (FireStationJson fireStationJson : CollectionUtils.emptyIfNull(safetyNetJson.getFirestations())) {
      var fireStationEntity = new FireStationEntity();
      fireStationEntity.setStation(Integer.valueOf(fireStationJson.getStation()));
      fireStationEntity.setAddress(fireStationJson.getAddress());
      if (fireStationDao.save(fireStationEntity) == null) {
        LOGGER.warn("Duplicate fire station in JSON file ({}, {}).", fireStationJson.getStation(), fireStationJson.getAddress());
      }
    }
  }

  /**
   * Loading AllergyEntity table
   * 
   * @param safetyNetJson SafetyNetJson object
   */
  public void setListAllergyEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        if (allergyDao.findIdAllergyByName(allergyJson) == null) {
          // Allergy not present
          var allergyEntity = new AllergyEntity();
          allergyEntity.setAllergy(allergyJson);
          if (allergyDao.save(allergyEntity) == null) {
            LOGGER.warn("Duplicate allergy in JSON file ({}).", allergyJson);
          }
        }
      }
    }
  }

  /**
   * Loading MedicationEntity table
   * 
   * @param safetyNetJson SafetyNetJson object
   */
  public void setListMedicationEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          String medication = medicationJson.split(":")[0];
          if (medicationDao.findIdMedicationByName(medication) == null) {
            // Medication not present
            var medicationEntity = new MedicationEntity();
            medicationEntity.setMedication(medication);
            if (medicationDao.save(medicationEntity) == null) {
              LOGGER.warn("Duplicate medication in JSON file ({}).", medication);
            }
          }
        }
      }
    }
  }

  /**
   * Loading MedicalRecordEntity table
   * 
   * @param safetyNetJson SafetyNetJson object
   */
  public void setListMedicalRecordEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      var personEntity = personDao.findPersonByName(medicalRecordJson.getFirstName(), medicalRecordJson.getLastName());

      // -----------------------------------------------------------------------------------------------
      personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecordJson.getBirthdate()));
      if (personDao.update(personEntity) == null) {
        LOGGER.warn("JSON file medical record date of birth cannot be updated ({}, {}).", personEntity.getFirstName(), personEntity.getLastName());
      }

      // -----------------------------------------------------------------------------------------------
      var medicalRecordEntity = new MedicalRecordEntity();
      medicalRecordEntity.setIdPerson(personEntity.getId());

      for (String allergyJson : medicalRecordJson.getAllergies()) {
        medicalRecordEntity.addMedicalRecordAllergiesItem(allergyUtils.allergyToMedicalRecordAllergyEntity(allergyJson));
      }

      for (String medicationJson : medicalRecordJson.getMedications()) {
        medicalRecordEntity.addMedicalRecordMedicationsItem(medicationUtils.medicationToMedicalRecordMedicationEntity(medicationJson));
      }

      // -----------------------------------------------------------------------------------------------
      if (medicalRecordDao.save(medicalRecordEntity) == null) {
        LOGGER.warn("Duplicate medical record in JSON file ({}, {}).", personEntity.getFirstName(), personEntity.getLastName());
      }
    }
  }
}
