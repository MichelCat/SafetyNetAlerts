package com.safetynet.safetynetalerts.api;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.safetynetalerts.business.PersonInfoBusiness;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonAndMedicalRecordInFirstNameLastName;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonInfoApiController is the Endpoint will perform the following actions via Get on people's information.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class PersonInfoApiController implements PersonInfoApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonInfoApiController.class);
    
    @Autowired
    private PersonInfoBusiness personInfoBusiness;

    /**
     * Read - Get people's information of each resident.
     * 
     * @param firstName First name
     * @param lastName Last name
     * @return List of email addresses 
     */
    public ResponseEntity<List<PersonAndMedicalRecordInFirstNameLastName>> getPersonInfo(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      LOGGER.debug("HTTP GET, List of people and medical records of people with the same last name ({}, {}).", firstName, lastName);
      List<Person> persons = personInfoBusiness.getAllPersonsWithTheSameName(firstName, lastName);
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}, {}).", firstName, lastName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
      }
      
      List<PersonAndMedicalRecordInFirstNameLastName> personsAndMedicalRecordInFirstNameLastName = new ArrayList<>();
      for (Person person : persons) {
        var personAndMedicalRecordInFirstNameLastName = new PersonAndMedicalRecordInFirstNameLastName();
        personAndMedicalRecordInFirstNameLastName.setFirstName(person.getFirstName());
        personAndMedicalRecordInFirstNameLastName.setLastName(person.getLastName());
        personAndMedicalRecordInFirstNameLastName.setAddress(person.getAddress());
        personAndMedicalRecordInFirstNameLastName.setAge(person.getAge());
        personAndMedicalRecordInFirstNameLastName.setEmail(person.getEmail());

        List<Medication> medications = personInfoBusiness.getMedicationByName(person.getId());
        personAndMedicalRecordInFirstNameLastName.setMedications(medications);
        
        List<Allergy> allergies = personInfoBusiness.getAllergyByName(person.getId());
        personAndMedicalRecordInFirstNameLastName.setAllergies(allergies);

        personsAndMedicalRecordInFirstNameLastName.add(personAndMedicalRecordInFirstNameLastName);
      }
      LOGGER.debug("HTTP GET, SUCCESSFUL ({}, {}).", firstName, lastName);
      return ResponseEntity.ok(personsAndMedicalRecordInFirstNameLastName);
    }

}
