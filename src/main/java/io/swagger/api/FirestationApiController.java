package io.swagger.api;

import io.swagger.business.FirestationBusiness;
import io.swagger.model.Error;
import io.swagger.model.Person;
import io.swagger.model.PersonsInFireStation;
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
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T21:09:55.880Z[GMT]")
@RestController
public class FirestationApiController implements FirestationApi {

    private static final Logger log = LoggerFactory.getLogger(FirestationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final FirestationBusiness personBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public FirestationApiController(ObjectMapper objectMapper, HttpServletRequest request, FirestationBusiness personBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.personBusiness = personBusiness;
    }

    public ResponseEntity<PersonsInFireStation> getFirestation(@Parameter(in = ParameterIn.QUERY, description = "the station number" ,schema=@Schema()) @Valid @RequestParam(value = "stationNumber", required = false) String stationNumber) {
      List<Person> persons = personBusiness.getPersonsLivingNearStation(stationNumber);
      int adults = personBusiness.getAdultsLivingIn(persons, stationNumber);
      int children = personBusiness.getChildrenLivingIn(persons, stationNumber);
      
      PersonsInFireStation personsInFireStation = new PersonsInFireStation();
      personsInFireStation.setPersons(persons);
      personsInFireStation.setAdultsCount(adults);
      personsInFireStation.setChildrenCount(children);
      
      return ResponseEntity.ok(personsInFireStation);
    }

}
