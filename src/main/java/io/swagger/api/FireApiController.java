package io.swagger.api;

import io.swagger.business.FireBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.Error;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInAddress;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-21T19:55:53.123Z[GMT]")
@RestController
public class FireApiController implements FireApi {

    private static final Logger log = LoggerFactory.getLogger(FireApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final FireBusiness fireBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public FireApiController(ObjectMapper objectMapper, HttpServletRequest request, FireBusiness fireBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.fireBusiness = fireBusiness;
    }

    public ResponseEntity<List<PersonAndMedicalRecordInAddress>> getFire(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
      List<Person> persons = fireBusiness.getPersonsLivingInAddress(address);
      
      List<PersonAndMedicalRecordInAddress> personsAndMedicalRecordInAddress = new ArrayList<>();
      for (Person person : persons) {
        PersonAndMedicalRecordInAddress personAndMedicalRecordInAddress = new PersonAndMedicalRecordInAddress();
        personAndMedicalRecordInAddress.setPerson(person);

        List<Medication> medications = fireBusiness.getMedicationByName(person.getFirstName(), person.getLastName());
        personAndMedicalRecordInAddress.setMedications(medications);
        
        List<Allergy> allergies = fireBusiness.getAllergyByName(person.getFirstName(), person.getLastName());
        personAndMedicalRecordInAddress.setAllergies(allergies);
        
        FireStation fireStation = fireBusiness.getFireStationByStationAddress(person.getAddress());
        personAndMedicalRecordInAddress.setFireStation(fireStation);

        personsAndMedicalRecordInAddress.add(personAndMedicalRecordInAddress);
      }
      return ResponseEntity.ok(personsAndMedicalRecordInAddress);
    }

}
