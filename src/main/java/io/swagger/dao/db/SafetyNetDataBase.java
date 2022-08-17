package io.swagger.dao.db;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
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

@Component
public class SafetyNetDataBase {
  private TreeSet<PersonEntity> personEntities = new TreeSet<>();
  private TreeSet<FireStationEntity> fireStationEntities = new TreeSet<>();
  private TreeSet<AllergyEntity> allergyEntities = new TreeSet<>();
  private TreeSet<MedicationEntity> medicationEntities = new TreeSet<>();
  private TreeSet<MedicalRecordEntity> medicalRecordEntities = new TreeSet<>();

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
  public TreeSet<PersonEntity> getPersonEntities() {
    return personEntities;
  }

  public void setPersonEntities(TreeSet<PersonEntity> personEntities) {
    this.personEntities = personEntities;
  }

  public TreeSet<FireStationEntity> getFireStationEntities() {
    return fireStationEntities;
  }

  public void setFireStationEntities(TreeSet<FireStationEntity> fireStationEntities) {
    this.fireStationEntities = fireStationEntities;
  }

  public TreeSet<AllergyEntity> getAllergyEntities() {
    return allergyEntities;
  }

  public void setAllergyEntities(TreeSet<AllergyEntity> allergyEntities) {
    this.allergyEntities = allergyEntities;
  }

  public TreeSet<MedicationEntity> getMedicationEntities() {
    return medicationEntities;
  }

  public void setMedicationEntities(TreeSet<MedicationEntity> medicationEntities) {
    this.medicationEntities = medicationEntities;
  }

  public TreeSet<MedicalRecordEntity> getMedicalRecordEntities() {
    return medicalRecordEntities;
  }

  public void setMedicalRecordEntities(TreeSet<MedicalRecordEntity> medicalRecordEntities) {
    this.medicalRecordEntities = medicalRecordEntities;
  }

  // -----------------------------------------------------------------------------------------------
  private void setListPersonEntity(SafetyNetJson safetyNetJson) {
    for (PersonJson personJson : safetyNetJson.getPersons()) {
      PersonEntity personEntity = new PersonEntity();
      personEntity.setId(null);
      personEntity.setFirstName(personJson.getFirstName());
      personEntity.setLastName(personJson.getLastName());
      personEntity.setAddress(personJson.getAddress());
      personEntity.setPhoneNumber(personJson.getPhone());
      personEntity.setZip(personJson.getZip());
      personEntity.setCity(personJson.getCity());
      personEntity.setEmail(personJson.getEmail());
      personEntities.add(personEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListFireStationEntity(SafetyNetJson safetyNetJson) {
    for (FireStationJson fireStationJson : safetyNetJson.getFirestations()) {
      FireStationEntity fireStationEntity = new FireStationEntity();
      fireStationEntity.setStation(Integer.valueOf(fireStationJson.getStation()));
      fireStationEntity.setAddress(fireStationJson.getAddress());
      fireStationEntities.add(fireStationEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListAllergyEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        AllergyEntity allergyEntity = new AllergyEntity();
        allergyEntity.setId(null);
        allergyEntity.setAllergy(allergyJson);
        allergyEntities.add(allergyEntity);
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListMedicationEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          MedicationEntity medicationEntity = new MedicationEntity();
          medicationEntity.setId(null);
          medicationEntity.setMedication(medicationJson.split(":")[0]);
          medicationEntities.add(medicationEntity);
        }
      }
    }
  }

  // -----------------------------------------------------------------------------------------------
  private void setListMedicalRecordEntity(SafetyNetJson safetyNetJson) {
    for (MedicalRecordJson medicalRecordJson : safetyNetJson.getMedicalrecords()) {
      PersonEntity personEntity = findPersonByName(medicalRecordJson.getFirstName(), medicalRecordJson.getLastName());

      // -----------------------------------------------------------------------------------------------
      personEntities.remove(personEntity);
      personEntity.setBirthdate(stringToDateConversion(medicalRecordJson.getBirthdate()));
      personEntities.add(personEntity);

      // -----------------------------------------------------------------------------------------------
      MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
      medicalRecordEntity.setIdPerson(personEntity.getId());
      medicalRecordEntity.setFirstName(medicalRecordJson.getFirstName());
      medicalRecordEntity.setLastName(medicalRecordJson.getLastName());

      List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();
      for (String allergyJson : medicalRecordJson.getAllergies()) {
        MedicalRecordAllergyEntity medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
        medicalRecordAllergyEntity.setIdAlergy(findIdAllergyByName(allergyJson));
        allergies.add(medicalRecordAllergyEntity);
      }
      medicalRecordEntity.setAllergies(allergies);

      List<MedicalRecordMedicationEntity> medications = new ArrayList<>();
      for (String medicationJson : medicalRecordJson.getMedications()) {
        if (medicationJson.split(":").length == 2) {
          MedicalRecordMedicationEntity medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
          medicalRecordMedicationEntity.setIdMedication(findIdMedicationByName(medicationJson.split(":")[0]));
          medicalRecordMedicationEntity.setDosage(medicationJson.split(":")[1]);
          medications.add(medicalRecordMedicationEntity);
        }
      }
      medicalRecordEntity.setMedications(medications);

      medicalRecordEntities.add(medicalRecordEntity);
    }
  }

  // -----------------------------------------------------------------------------------------------
  private PersonEntity findPersonByName(String firstName, String lastName) {
    Iterator<PersonEntity> iterator = personEntities.iterator();
    while (iterator.hasNext()) {
      PersonEntity personEntity = iterator.next();
      if (personEntity.getFirstName().equalsIgnoreCase(firstName) && personEntity.getLastName().equalsIgnoreCase(lastName)) {
        return personEntity;
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  private Date stringToDateConversion(String stringDate) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").parse(stringDate));
    } catch (ParseException e) {
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  private Integer findIdAllergyByName(String allergy) {
    return allergyEntities.stream().filter(element -> element.getAllergy().equalsIgnoreCase(allergy)).findAny().get().getId();
  }

  // -----------------------------------------------------------------------------------------------
  private Integer findIdMedicationByName(String medication) {
    return medicationEntities.stream().filter(element -> element.getMedication().equalsIgnoreCase(medication)).findAny().get().getId();
  }

}
