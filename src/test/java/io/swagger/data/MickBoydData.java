package io.swagger.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.MedicalRecord;
import io.swagger.model.Medication;
import io.swagger.model.Person;

public class MickBoydData {
  
  // -----------------------------------------------------------------------------------------------
  // Mick Boyd
  // -----------------------------------------------------------------------------------------------
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
  
  public static Medication getMedication() {
    Medication medication = new Medication();
    medication.setMedication("aznol:350mg");
    return medication;
  }
  
  public static Allergy getAllergy() {
    Allergy allergy = new Allergy();
    allergy.setAllergy("nillacilan");
    return allergy;
  }
  
  public static MedicalRecord getMedicalRecord() {
    MedicalRecord medicalRecord = new MedicalRecord();
    medicalRecord.setId(1);
    medicalRecord.setFirstName("Mick");
    medicalRecord.setLastName("Boyd");
    medicalRecord.setBirthdate("03/06/1984");
    
    List<Allergy> allergies = new ArrayList<>();
    allergies.add(getAllergy());
    medicalRecord.setAllergies(allergies);
    
    List<Medication> medications = new ArrayList<>();
    medications.add(getMedication());
    medicalRecord.setMedications(medications);
    
    return medicalRecord;
  }
  
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
  
  public static MedicalRecord getUpdateMedicalRecord() {
    MedicalRecord medicalRecord = new MedicalRecord();
    medicalRecord.setId(1);
    medicalRecord.setFirstName("Mick");
    medicalRecord.setLastName("Boyd");
    medicalRecord.setBirthdate("03/06/1980");
    
    List<Allergy> allergies = new ArrayList<>();
    Allergy allergy = new Allergy();
    allergy.setAllergy("peanut");
    allergies.add(allergy);
    medicalRecord.setAllergies(allergies);
    
    List<Medication> medications = new ArrayList<>();
    Medication medication = new Medication();
    medication.setMedication("tetracyclaz:650mg");
    medications.add(medication);
    medicalRecord.setMedications(medications);
    
    return medicalRecord;
  }
}
