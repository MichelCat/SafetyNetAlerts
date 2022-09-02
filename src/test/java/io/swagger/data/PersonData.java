package io.swagger.data;

import java.util.Date;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

public class PersonData {

  public static Person getPersonJohnBoyd() {
    Person person = new Person();
    person.setId(1);
    person.setFirstName("John");
    person.setLastName("Boyd");
    person.setAddress("1509 Culver St");
    person.setPhoneNumber("841-874-6512");
    person.setZipCode("97451");
    person.setAge(38);
    person.setCity("Culver");
    person.setBirthdate("03/06/1984");
    person.setEmail("jaboyd@email.com");
    return(person);
  }
  
  public static PersonEntity getPersonEntityJohnBoyd() {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setFirstName("John");
    personEntity.setLastName("Boyd");
    personEntity.setAddress("1509 Culver St");
    personEntity.setPhoneNumber("841-874-6512");
    personEntity.setZip("97451");
    personEntity.setCity("Culver");
    personEntity.setBirthdate(new Date("1984/03/06"));
    personEntity.setEmail("jaboyd@email.com");
    return(personEntity);
  }
  
  public static String getJsonJohnBoyd() {
    String jsonString= "{"
        + "\"id\": 1,"
        + "\"firstName\": \"John\","
        + "\"lastName\": \"Boyd\","
        + "\"address\": \"1509 Culver St\","
        + "\"phoneNumber\": \"841-874-6512\","
        + "\"zipCode\": \"97451\","
        + "\"age\": 38,"
        + "\"city\": \"Culver\","
        + "\"birthdate\": \"03/06/1984\","
        + "\"email\": \"jaboyd@email.com\""
        + "}";
    return jsonString;
  }
  
}
