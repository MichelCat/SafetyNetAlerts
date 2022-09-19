package io.swagger.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.AllergyEntity;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.dao.db.entities.MedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.dao.json.entities.FireStationJson;
import io.swagger.dao.json.entities.MedicalRecordJson;
import io.swagger.dao.json.entities.PersonJson;
import io.swagger.dao.json.entities.SafetyNetJson;
import io.swagger.utils.AllergyUtils;
import io.swagger.utils.DateUtils;
import io.swagger.utils.MedicationUtils;

@Service
public class LoadJsonFileInDatabaseBusiness {
  
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

  // -----------------------------------------------------------------------------------------------
  public void loadDataBase(String resourceLocation) {
    SafetyNetJson safetyNetJson;
    try {
      safetyNetJson = readFileJson(resourceLocation);
      setListPersonEntity(safetyNetJson);
      setListFireStationEntity(safetyNetJson);
      setListAllergyEntity(safetyNetJson);
      setListMedicationEntity(safetyNetJson);
      setListMedicalRecordEntity(safetyNetJson);
    } catch (Exception e) {
    } 
  }
  
  // -----------------------------------------------------------------------------------------------
  public SafetyNetJson readFileJson(String resourceLocation) throws StreamReadException, DatabindException, IOException {
    var objectMapper = new ObjectMapper();
    File dataJson = ResourceUtils.getFile("classpath:" + resourceLocation);
    return objectMapper.readValue(dataJson, SafetyNetJson.class);
  }
  
  // -----------------------------------------------------------------------------------------------
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
      personEntity = personDao.save(personEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListFireStationEntity(SafetyNetJson safetyNetJson) {
    for (FireStationJson fireStationJson : CollectionUtils.emptyIfNull(safetyNetJson.getFirestations())) {
      var fireStationEntity = new FireStationEntity();
      fireStationEntity.setStation(Integer.valueOf(fireStationJson.getStation()));
      fireStationEntity.setAddress(fireStationJson.getAddress());
      fireStationEntity = fireStationDao.save(fireStationEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListAllergyEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        var allergyEntity = new AllergyEntity();
        allergyEntity.setAllergy(allergyJson);
        allergyEntity = allergyDao.save(allergyEntity);
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListMedicationEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          var medicationEntity = new MedicationEntity();
          medicationEntity.setMedication(medicationJson.split(":")[0]);
          medicationEntity = medicationDao.save(medicationEntity);
        }
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListMedicalRecordEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : CollectionUtils.emptyIfNull(safetyNetJson.getMedicalrecords())) {
      var personEntity = personDao.findPersonByName(medicalRecordJson.getFirstName(), medicalRecordJson.getLastName());

      // -----------------------------------------------------------------------------------------------
      personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecordJson.getBirthdate()));
      personDao.update(personEntity);

      // -----------------------------------------------------------------------------------------------
      var medicalRecordEntity = new MedicalRecordEntity();
      medicalRecordEntity.setIdPerson(personEntity.getId());

      List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        CollectionUtils.addIgnoreNull(allergies
            , allergyUtils.allergyToMedicalRecordAllergyEntity(allergyJson));
      }
      medicalRecordEntity.setAllergies(allergies);

      List<MedicalRecordMedicationEntity> medications = new ArrayList<>();
      for (String medicationJson : medicalRecordJson.getMedications()) {
        CollectionUtils.addIgnoreNull(medications
            , medicationUtils.medicationToMedicalRecordMedicationEntity(medicationJson));
      }
      medicalRecordEntity.setMedications(medications);

      // -----------------------------------------------------------------------------------------------
      medicalRecordEntity = medicalRecordDao.save(medicalRecordEntity);
    }
  }
}
