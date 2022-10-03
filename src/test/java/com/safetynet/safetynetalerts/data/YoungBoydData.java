package com.safetynet.safetynetalerts.data;

import java.util.Date;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

public class YoungBoydData {

  // -----------------------------------------------------------------------------------------------
  // Young Boyd
  // -----------------------------------------------------------------------------------------------
  public static Person getPerson() {
    Person person = new Person();
    person.setId(1);
    person.setFirstName("Young");
    person.setLastName("Boyd");
    person.setAddress("1234 Wall Street");
    person.setPhoneNumber("841-874-6512");
    person.setZipCode("97451");
    person.setAge(10);
    person.setCity("Culver");
    person.setBirthdate("18/02/2012");
    person.setEmail("yoboyd@email.com");
    return(person);
  }
  
  public static PersonEntity getPersonEntity() {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setFirstName("Young");
    personEntity.setLastName("Boyd");
    personEntity.setAddress("1234 Wall Street");
    personEntity.setPhoneNumber("841-874-6512");
    personEntity.setZip("97451");
    personEntity.setCity("Culver");
    personEntity.setBirthdate(new Date("2012/02/18"));
    personEntity.setEmail("yoboyd@email.com");
    return(personEntity);
  }
  
  public static String getJson() {
    String jsonString= "{"
        + "\"id\": 1,"
        + "\"firstName\": \"Young\","
        + "\"lastName\": \"Boyd\","
        + "\"address\": \"1234 Wall Street\","
        + "\"phoneNumber\": \"841-874-6512\","
        + "\"zipCode\": \"97451\","
        + "\"age\": 10,"
        + "\"city\": \"Culver\","
        + "\"birthdate\": \"18/02/2012\","
        + "\"email\": \"yoboyd@email.com\""
        + "}";
    return jsonString;
  }
  
  // -----------------------------------------------------------------------------------------------
  public static Medication getMedication() {
    return null;
  }
  
  public static Allergy getAllergyPeanut() {
    Allergy allergy = new Allergy();
    allergy.setAllergy("peanut");
    return allergy;
  }
}
