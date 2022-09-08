package io.swagger.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import io.swagger.utils.DateUtils;

@Service
public class LoadJsonFileInDatabaseBusiness {
  
  @Autowired
  public DateUtils dateUtils;
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
  public SafetyNetJson readFileJson() throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    File dataJson = ResourceUtils.getFile("classpath:data.json");
    return objectMapper.readValue(dataJson, SafetyNetJson.class);
  }
  
  // -----------------------------------------------------------------------------------------------
  public void setListPersonEntity(SafetyNetJson safetyNetJson) {
    for (PersonJson personJson : safetyNetJson.getPersons()) {
      PersonEntity personEntity = new PersonEntity();
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
    for (FireStationJson fireStationJson : safetyNetJson.getFirestations()) {
      FireStationEntity fireStationEntity = new FireStationEntity();
      fireStationEntity.setStation(Integer.valueOf(fireStationJson.getStation()));
      fireStationEntity.setAddress(fireStationJson.getAddress());
      fireStationEntity = fireStationDao.save(fireStationEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListAllergyEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        AllergyEntity allergyEntity = new AllergyEntity();
        allergyEntity.setAllergy(allergyJson);
        allergyEntity = allergyDao.save(allergyEntity);
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListMedicationEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          MedicationEntity medicationEntity = new MedicationEntity();
          medicationEntity.setMedication(medicationJson.split(":")[0]);
          medicationEntity = medicationDao.save(medicationEntity);
        }
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  public void setListMedicalRecordEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      PersonEntity personEntity = personDao.findPersonByName(medicalRecordJson.getFirstName(), medicalRecordJson.getLastName());

      // -----------------------------------------------------------------------------------------------
      personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecordJson.getBirthdate()));
      personDao.update(personEntity);

      // -----------------------------------------------------------------------------------------------
      MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
      medicalRecordEntity.setIdPerson(personEntity.getId());

      List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        MedicalRecordAllergyEntity medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
        medicalRecordAllergyEntity.setIdAlergy(allergyDao.findIdAllergyByName(allergyJson));
        allergies.add(medicalRecordAllergyEntity);
      }
      medicalRecordEntity.setAllergies(allergies);

      List<MedicalRecordMedicationEntity> medications = new ArrayList<>();
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          MedicalRecordMedicationEntity medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
          medicalRecordMedicationEntity.setIdMedication(medicationDao.findIdMedicationByName(medicationJson.split(":")[0]));
          medicalRecordMedicationEntity.setDosage(medicationJson.split(":")[1]);
          medications.add(medicalRecordMedicationEntity);
        }
      }
      medicalRecordEntity.setMedications(medications);

      // -----------------------------------------------------------------------------------------------
      medicalRecordEntity = medicalRecordDao.save(medicalRecordEntity);
    }
  }
}
