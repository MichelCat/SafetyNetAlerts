package com.safetynet.safetynetalerts.data;

import java.util.Date;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

/**
 * MickBoydData is the class containing Mick's test set
 * 
 * @author MC
 * @version 1.0
 */
public class MickBoydData {
  
  // -----------------------------------------------------------------------------------------------
  // Mick Boyd
  // -----------------------------------------------------------------------------------------------
  /**
   * Set of test Person
   */
  public static Person getPerson() {
    Person person = new Person();
    person.setId(1);
    person.setFirstName("Mick");
    person.setLastName("Boyd");
    person.setAddress("1234 Wall Street");
    person.setPhoneNumber("841-874-6512");
    person.setZipCode("97451");
    person.setAge(38);
    person.setCity("Culver");
    person.setBirthdate("03/06/1984");
    person.setEmail("miboyd@email.com");
    return(person);
  }
  
  /**
   * Set of test PersonEntity
   */
  public static PersonEntity getPersonEntity() {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setFirstName("Mick");
    personEntity.setLastName("Boyd");
    personEntity.setAddress("1234 Wall Street");
    personEntity.setPhoneNumber("841-874-6512");
    personEntity.setZip("97451");
    personEntity.setCity("Culver");
    personEntity.setBirthdate(new Date("1984/06/03"));
    personEntity.setEmail("miboyd@email.com");
    return(personEntity);
  }
  
  /**
   * Set of test JSON file Person
   */
  public static String getJson() {
    String jsonString= "{"
        + "\"id\": 1,"
        + "\"firstName\": \"Mick\","
        + "\"lastName\": \"Boyd\","
        + "\"address\": \"1234 Wall Street\","
        + "\"phoneNumber\": \"841-874-6512\","
        + "\"zipCode\": \"97451\","
        + "\"age\": 38,"
        + "\"city\": \"Culver\","
        + "\"birthdate\": \"03/06/1984\","
        + "\"email\": \"miboyd@email.com\""
        + "}";
    return jsonString;
  }
  
  // -----------------------------------------------------------------------------------------------
  /**
   * Set of test Medication
   */
  public static Medication getMedicationAznol() {
    Medication medication = new Medication();
    medication.setMedication("aznol:350mg");
    return medication;
  }
  
  /**
   * Set of test MedicationEntity
   */
  public static MedicationEntity getMedicationEntityAznol() {
    MedicationEntity medicationEntity = new MedicationEntity();
    medicationEntity.setId(1);
    medicationEntity.setMedication("aznol");
    return medicationEntity;
  }
  
  /**
   * Set of test Allergy nillacilan
   */
  public static Allergy getAllergyNillacilan() {
    Allergy allergy = new Allergy();
    allergy.setAllergy("nillacilan");
    return allergy;
  }
  
  /**
   * Set of test AllergyEntity nillacilan
   */
  public static AllergyEntity getAllergyEntityNillacilan() {
    AllergyEntity allergyEntity = new AllergyEntity();
    allergyEntity.setId(1);
    allergyEntity.setAllergy("nillacilan");
    return allergyEntity;
  }
  
  /**
   * Set of test MedicalRecord
   */
  public static MedicalRecord getMedicalRecord() {
    MedicalRecord medicalRecord = new MedicalRecord();
    medicalRecord.setId(1);
    medicalRecord.setFirstName("Mick");
    medicalRecord.setLastName("Boyd");
    medicalRecord.setBirthdate("03/06/1984");
    medicalRecord.addAllergiesItem(getAllergyNillacilan());
    medicalRecord.addMedicationsItem(getMedicationAznol());
    return medicalRecord;
  }
  
  /**
   * Set of test MedicalRecordEntity
   */
  public static MedicalRecordEntity getMedicalRecordEntity() {
    MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
    medicalRecordEntity.setIdPerson(1);
    
    MedicalRecordAllergyEntity medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
    medicalRecordAllergyEntity.setIdAlergy(getAllergyEntityNillacilan().getId());
    medicalRecordEntity.addMedicalRecordAllergiesItem(medicalRecordAllergyEntity);
    
    MedicalRecordMedicationEntity medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
    medicalRecordMedicationEntity.setIdMedication(getMedicationEntityAznol().getId());
    medicalRecordMedicationEntity.setDosage(getMedicationAznol().getMedication().split(":")[1]);
    medicalRecordEntity.addMedicalRecordMedicationsItem(medicalRecordMedicationEntity);
    return medicalRecordEntity;
  }
  
  /**
   * Set of test JSON file MedicalRecord
   */
  public static String getMedicalRecordJson() {
    String jsonString= "{"
        + "\"allergies\": ["
          + "{"
            + "\"allergy\": \"nillacilan\""
          + "}"
        + "],"
        + "\"birthdate\": \"03/06/1984\","
        + "\"firstName\": \"Mick\","
        + "\"id\": 0,"
        + "\"lastName\": \"Boyd\","
        + "\"medications\": ["
          + "{"
            + "\"medication\": \"aznol:350mg\""
          + "}"
        + "]"
        + "}";
    return jsonString;
  }
  
  // -----------------------------------------------------------------------------------------------
  /**
   * Set of test MedicalRecord update
   */
  public static MedicalRecord getUpdateMedicalRecord() {
    MedicalRecord medicalRecord = new MedicalRecord();
    medicalRecord.setId(1);
    medicalRecord.setFirstName("Mick");
    medicalRecord.setLastName("Boyd");
    medicalRecord.setBirthdate("03/06/1980");
    
    Allergy allergy = new Allergy();
    allergy.setAllergy("peanut");
    medicalRecord.addAllergiesItem(allergy);
    
    Medication medication = new Medication();
    medication.setMedication("tetracyclaz:650mg");
    medicalRecord.addMedicationsItem(medication);
    
    return medicalRecord;
  }
  
  /**
   * Set of test AllergyEntity peanut
   */
  public static AllergyEntity getAllergyEntityPeanut() {
    AllergyEntity allergyEntity = new AllergyEntity();
    allergyEntity.setId(2);
    allergyEntity.setAllergy("peanut");
    return allergyEntity;
  }

  /**
   * Set of test AllergyEntity tetracyclaz
   */
  public static MedicationEntity getMedicationEntityTetracyclaz() {
    MedicationEntity medicationEntity = new MedicationEntity();
    medicationEntity.setId(2);
    medicationEntity.setMedication("tetracyclaz");
    return medicationEntity;
  }
}
