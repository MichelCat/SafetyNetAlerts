package io.swagger.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Person;

@Service
public class MickBoydData {

  @Autowired
  private PersonDao personDao;
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private MedicationDao medicationDao;
  
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
    person.setEmail("jaboyd@email.com");
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
    personEntity.setBirthdate(new Date("1984/03/06"));
    personEntity.setEmail("jaboyd@email.com");
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
        + "\"email\": \"jaboyd@email.com\""
        + "}";
    return jsonString;
  }

  public static MedicalRecordEntity getMedicalRecordEntity() {
    List<MedicalRecordAllergyEntity> medicalRecordAllergyEntity = new ArrayList<>();
    
    
    
    List<MedicalRecordMedicationEntity> medicalRecordMedicationEntity = new ArrayList<>(); 
    
    
//    Integer idPerson = personDao.findPersonByName("","").getId();
    
    MedicalRecordEntity medicalRecordEntity = new MedicalRecordEntity();
    medicalRecordEntity.setIdPerson(1);
    medicalRecordEntity.setAllergies(medicalRecordAllergyEntity);
    medicalRecordEntity.setMedications(medicalRecordMedicationEntity);
    return medicalRecordEntity;
  }
  
}
