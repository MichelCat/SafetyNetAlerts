package io.swagger.api;

import io.swagger.business.PersonInfoBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.Error;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInFirstNameLastName;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-29T17:07:42.572Z[GMT]")
@RestController
public class PersonInfoApiController implements PersonInfoApi {

    private static final Logger log = LoggerFactory.getLogger(PersonInfoApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final PersonInfoBusiness personInfoBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public PersonInfoApiController(ObjectMapper objectMapper, HttpServletRequest request, PersonInfoBusiness personInfoBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
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
