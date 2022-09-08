package io.swagger.api;

import io.swagger.business.FirestationBusiness;
import io.swagger.model.Error;
import io.swagger.model.FireStation;
import io.swagger.model.Person;
import io.swagger.model.PersonInFireStation;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-09-05T20:43:00.293Z[GMT]")
@RestController
public class FirestationApiController implements FirestationApi {

    private static final Logger log = LoggerFactory.getLogger(FirestationApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final FirestationBusiness firestationBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public FirestationApiController(ObjectMapper objectMapper, HttpServletRequest request, FirestationBusiness firestationBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.firestationBusiness = firestationBusiness;
    }

    public ResponseEntity<Void> deleteFirestation(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stationNumber", required = false) String stationNumber,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
      try {
        firestationBusiness.deleteFireStation(stationNumber, address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
    }

    public ResponseEntity<PersonInFireStation> getFirestation(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stationNumber", required = false) String stationNumber) {
      List<Person> persons = firestationBusiness.getPersonsLivingNearStation(stationNumber);
      if (persons == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      int adults = firestationBusiness.getAdultsLivingIn(persons, stationNumber);
      int children = firestationBusiness.getChildrenLivingIn(persons, stationNumber);
      
      PersonInFireStation personInFireStation = new PersonInFireStation();
      personInFireStation.setPersons(persons);
      personInFireStation.setAdultsCount(adults);
      personInFireStation.setChildrenCount(children);
      
      return ResponseEntity.ok(personInFireStation);
    }

    public ResponseEntity<FireStation> postFirestation(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody FireStation body) {
      FireStation fireStation = firestationBusiness.saveFireStation(body);
      if (fireStation == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(fireStation.getId())
                    .toUri();
      return ResponseEntity.created(location).body(fireStation);
    }

    public ResponseEntity<FireStation> putFirestation(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody FireStation body) {
      FireStation fireStation = firestationBusiness.updateFireStation(body);
      if (fireStation == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      return ResponseEntity.ok().body(fireStation);
    }

}
