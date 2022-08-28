package io.swagger.api;

import io.swagger.business.FloodBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.Error;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInFireStation;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-27T21:21:17.443Z[GMT]")
@RestController
public class FloodApiController implements FloodApi {

    private static final Logger log = LoggerFactory.getLogger(FloodApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final FloodBusiness floodBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public FloodApiController(ObjectMapper objectMapper, HttpServletRequest request, FloodBusiness floodBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.floodBusiness = floodBusiness;
    }

    public ResponseEntity<List<PersonAndMedicalRecordInFireStation>> getFloodStations(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stations", required = false) String stations) {
      TreeMap<String, PersonAndMedicalRecordInFireStation> personsAndMedicalRecordInFireStation = new TreeMap<String, PersonAndMedicalRecordInFireStation>();
      
      List<Person> persons = floodBusiness.getPersonsLivingNearStation(stations);
      for (Person person : persons) {
        PersonAndMedicalRecordInFireStation personAndMedicalRecordInFireStation = new PersonAndMedicalRecordInFireStation();
        personAndMedicalRecordInFireStation.setAddress(person.getAddress());
        personAndMedicalRecordInFireStation.setLastName(person.getLastName());
        personAndMedicalRecordInFireStation.setPhoneNumber(person.getPhoneNumber());
        personAndMedicalRecordInFireStation.setAge(person.getAge());

        List<Medication> medications = floodBusiness.getMedicationByName(person.getFirstName(), person.getLastName());
        personAndMedicalRecordInFireStation.setMedications(medications);
        
        List<Allergy> allergies = floodBusiness.getAllergyByName(person.getFirstName(), person.getLastName());
        personAndMedicalRecordInFireStation.setAllergies(allergies);
        
        FireStation fireStation = floodBusiness.getFireStationByStationAddress(person.getAddress());
        personAndMedicalRecordInFireStation.stationNumber(fireStation.getId());

        personsAndMedicalRecordInFireStation.put(person.getAddress() + person.getLastName(), personAndMedicalRecordInFireStation);
      }
      return ResponseEntity.ok(new ArrayList<PersonAndMedicalRecordInFireStation>(personsAndMedicalRecordInFireStation.values()));
    }

}
