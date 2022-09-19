package io.swagger.api;

import io.swagger.business.PersonInfoBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInFirstNameLastName;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonInfoApiController implements PersonInfoApi {

    private static final Logger log = LoggerFactory.getLogger(PersonInfoApiController.class);
    
    private final PersonInfoBusiness personInfoBusiness;

    @Autowired
    public PersonInfoApiController(PersonInfoBusiness personInfoBusiness) {
        this.personInfoBusiness = personInfoBusiness;
    }

    public ResponseEntity<List<PersonAndMedicalRecordInFirstNameLastName>> getPersonInfo(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      List<PersonAndMedicalRecordInFirstNameLastName> personsAndMedicalRecordInFirstNameLastName = new ArrayList<>();
      
      List<Person> persons = personInfoBusiness.getAllPersonsWithTheSameName(firstName, lastName);
      for (Person person : persons) {
        PersonAndMedicalRecordInFirstNameLastName personAndMedicalRecordInFirstNameLastName = new PersonAndMedicalRecordInFirstNameLastName();
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
      return ResponseEntity.ok(personsAndMedicalRecordInFirstNameLastName);
    }

}
