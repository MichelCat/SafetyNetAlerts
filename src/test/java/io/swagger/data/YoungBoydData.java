package io.swagger.data;

import java.util.Date;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

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
    person.setAge(38);
    person.setCity("Culver");
    person.setBirthdate("18/02/2012");
    person.setEmail("tenz@email.com");
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
    personEntity.setBirthdate(new Date("2012/18/02"));
    personEntity.setEmail("tenz@email.com");
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
        + "\"age\": 38,"
        + "\"city\": \"Culver\","
        + "\"birthdate\": \"18/02/2012\","
        + "\"email\": \"tenz@email.com\""
        + "}";
    return jsonString;
  }

}
