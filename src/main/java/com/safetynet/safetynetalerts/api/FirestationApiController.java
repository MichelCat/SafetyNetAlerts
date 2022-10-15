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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.safetynet.safetynetalerts.business.FirestationBusiness;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonInFireStation;
import com.safetynet.safetynetalerts.model.UpdateFireStation;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * FirestationApiController is the Endpoint will perform the following actions via Get/Post/Put/Delete with HTTP on fire stations.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class FirestationApiController implements FirestationApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirestationApiController.class);

    @Autowired
    private FirestationBusiness firestationBusiness;

    /**
     * Read - Get the list of inhabitants living at the given address with the number of the fire station serving it, name,
     * telephone number, age and medical history.
     * 
     * @param stationNumber Station number
     * @return List of people served by the fire station. 
     */
    public ResponseEntity<PersonInFireStation> getFirestation(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stationNumber", required = false) String stationNumber) {
      LOGGER.debug("HTTP GET, List of people covered by the fire station ({}).", stationNumber);
      List<Person> persons = firestationBusiness.getPersonsLivingNearStation(stationNumber);
      int adults = firestationBusiness.getAdultsLivingIn(persons);
      int children = firestationBusiness.getChildrenLivingIn(persons);
      
      var personInFireStation = new PersonInFireStation();
      personInFireStation.setPersons(persons);
      personInFireStation.setAdultsCount(adults);
      personInFireStation.setChildrenCount(children);
      
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}).", stationNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(personInFireStation);
      }
      LOGGER.info("HTTP GET, SUCCESSFUL ({}).", stationNumber);
      return ResponseEntity.ok(personInFireStation);
    }

    /**
     * Create - Add a new fire station
     * 
     * @param body An object fire station
     * @return The fire station object saved
     */
    public ResponseEntity<FireStation> postFirestation(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody FireStation body) {
      LOGGER.debug("HTTP POST, Add fire station/address mapping ({}, {}).", body.getId(), body.getAddress());
      FireStation fireStation = firestationBusiness.saveFireStation(body);
      if (fireStation == null) {
        LOGGER.debug("HTTP POST, BAD_REQUEST ({}, {}).", body.getId(), body.getAddress());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(fireStation.getId())
                    .toUri();
      LOGGER.info("HTTP POST, CREATED ({}, {}).", body.getId(), body.getAddress());
      return ResponseEntity.created(location).body(fireStation);
    }

    /**
     * Update - Update an existing fire station
     * 
     * @param body An object fire station
     * @return The fire station object updated
     */
    public ResponseEntity<FireStation> putFirestation(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody UpdateFireStation body) {
      LOGGER.debug("HTTP PUT, Update fire station/address mapping ({}, {}) in ({}, {}).", body.getOldStation(), body.getAddress(), body.getNewStation(), body.getAddress());
      FireStation fireStation = firestationBusiness.updateFireStation(body);
      if (fireStation == null) {
        LOGGER.debug("HTTP PUT, BAD_REQUEST ({}, {}) in ({}, {}).", body.getOldStation(), body.getAddress(), body.getNewStation(), body.getAddress());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.info("HTTP PUT, SUCCESSFUL ({}, {}) in ({}, {}).", body.getOldStation(), body.getAddress(), body.getNewStation(), body.getAddress());
      return ResponseEntity.ok().body(fireStation);
    }

    /**
     * Delete - Delete an fire station
     * 
     * @param stationNumber - The station number of the fire station to delete
     * @param address - The address of the fire station to delete
     */
    public ResponseEntity<Void> deleteFirestation(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stationNumber", required = false) String stationNumber,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
      LOGGER.debug("HTTP DELETE, Delete fire station/address mapping ({}, {}).", stationNumber, address);
      if (firestationBusiness.deleteFireStation(stationNumber, address) == false) {
        LOGGER.debug("HTTP DELETE, BAD_REQUEST ({}, {}).", stationNumber, address);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.info("HTTP DELETE, NO_CONTENT ({}, {}).", stationNumber, address);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
