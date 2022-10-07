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
import com.safetynet.safetynetalerts.business.PhoneAlertBusiness;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PhoneInFireStation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * PhoneAlertApiController is the Endpoint will perform the following actions via Get with HTTP on phone number to alert.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class PhoneAlertApiController implements PhoneAlertApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneAlertApiController.class);

    @Autowired
    private PhoneAlertBusiness phoneAlertBusiness;

    /**
     * Read - Get the phone numbers of residents served by the fire station
     * 
     * @param firestation Fire station
     * @return List of telephone numbers served by the fire station 
     */
    public ResponseEntity<List<PhoneInFireStation>> getPhoneAlert(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firestation", required = false) String firestation) {
      LOGGER.debug("HTTP GET, List of telephone numbers of residents served by the fire station ({}).", firestation);
      List<Person> persons = phoneAlertBusiness.getPersonsLivingNearStation(firestation);
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}).", firestation);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
      }
      
      List<PhoneInFireStation> phonesInFireStation = new ArrayList<>();
      for (Person person : persons) {
        var phoneInFireStation = new PhoneInFireStation();
        phoneInFireStation.setPerson(person);
        phonesInFireStation.add(phoneInFireStation);
      }
      LOGGER.debug("HTTP GET, SUCCESSFUL ({}).", firestation);
      return ResponseEntity.ok(phonesInFireStation);
    }
}
