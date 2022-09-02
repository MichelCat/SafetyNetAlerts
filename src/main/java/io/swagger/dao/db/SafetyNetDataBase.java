package io.swagger.dao.db;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Component
public class SafetyNetDataBase {
  
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
  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() throws StreamReadException, DatabindException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    File dataJson = ResourceUtils.getFile("classpath:data.json");
    SafetyNetJson safetyNetJson = objectMapper.readValue(dataJson, SafetyNetJson.class);

    this.setListPersonEntity(safetyNetJson);
    this.setListFireStationEntity(safetyNetJson);
    this.setListAllergyEntity(safetyNetJson);
    this.setListMedicationEntity(safetyNetJson);
    this.setListMedicalRecordEntity(safetyNetJson);
  }

  // -----------------------------------------------------------------------------------------------
  private void setListPersonEntity(SafetyNetJson safetyNetJson) {
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
  private void setListFireStationEntity(SafetyNetJson safetyNetJson) {
    for (FireStationJson fireStationJson : safetyNetJson.getFirestations()) {
      FireStationEntity fireStationEntity = new FireStationEntity();
      fireStationEntity.setStation(Integer.valueOf(fireStationJson.getStation()));
      fireStationEntity.setAddress(fireStationJson.getAddress());
      fireStationEntity = fireStationDao.save(fireStationEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListAllergyEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        AllergyEntity allergyEntity = new AllergyEntity();
        allergyEntity.setAllergy(allergyJson);
        allergyEntity = allergyDao.save(allergyEntity);
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListMedicationEntity(SafetyNetJson safetyNetJson) {
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
  private void setListMedicalRecordEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      PersonEntity personEntity = personDao.findPersonByName(medicalRecordJson.getFirstName(), medicalRecordJson.getLastName());

      // -----------------------------------------------------------------------------------------------
      personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecordJson.getBirthdate()));
      personDao.update(personEntity);

      // -----------------------------------------------------------------------------------------------
      MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
      medicalRecordEntity.setIdPerson(personEntity.getId());
      medicalRecordEntity.setFirstName(medicalRecordJson.getFirstName());
      medicalRecordEntity.setLastName(medicalRecordJson.getLastName());

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
