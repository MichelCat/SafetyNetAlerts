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
import com.safetynet.safetynetalerts.business.FireBusiness;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonAndMedicalRecordInAddress;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * FireApiController is the Endpoint will perform the following actions via Get with HTTP on the inhabitants living at the address.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class FireApiController implements FireApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FireApiController.class);

    @Autowired
    private FireBusiness fireBusiness;

    /**
     * Read - Get the list of inhabitants living at the given address.
     * 
     * @param address Search address 
     * @return List of inhabitants living at the given address
     */
    public ResponseEntity<List<PersonAndMedicalRecordInAddress>> getFire(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
      LOGGER.debug("HTTP GET, List of residents living at an address and records medical ({}).", address);
      List<Person> persons = fireBusiness.getPersonsLivingInAddress(address);
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}).", address);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
      }
      
      List<PersonAndMedicalRecordInAddress> personsAndMedicalRecordInAddress = new ArrayList<>();
      for (Person person : persons) {
        var personAndMedicalRecordInAddress = new PersonAndMedicalRecordInAddress();
        personAndMedicalRecordInAddress.setPerson(person);

        List<Medication> medications = fireBusiness.getMedicationByName(person.getId());
        personAndMedicalRecordInAddress.setMedications(medications);
        
        List<Allergy> allergies = fireBusiness.getAllergyByName(person.getId());
        personAndMedicalRecordInAddress.setAllergies(allergies);
        
        List<FireStation> fireStations = fireBusiness.getFireStationByStationAddress(person.getAddress());
        personAndMedicalRecordInAddress.setFireStations(fireStations);

        personsAndMedicalRecordInAddress.add(personAndMedicalRecordInAddress);
      }
      LOGGER.debug("HTTP GET, SUCCESSFUL ({}).", address);
      return ResponseEntity.ok(personsAndMedicalRecordInAddress);
    }

}
