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
import com.safetynet.safetynetalerts.business.FloodBusiness;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.PersonAndMedicalRecordInFireStation;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * FloodApiController is the Endpoint class that will allow Gets to be performed with HTTP on the households served by the fire station.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class FloodApiController implements FloodApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FloodApiController.class);

    @Autowired
    private FloodBusiness floodBusiness;

    /**
     * Read - Get a list of all households served by the station. This list groups people by address.
     * It includes the name, phone number and age of residents, and medical history.
     * 
     * @param stations List of fire stations
     * @return List of households served by the station
     */
    public ResponseEntity<List<PersonAndMedicalRecordInFireStation>> getFloodStations(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stations", required = false) String stations) {
      LOGGER.debug("HTTP GET, List of people covered by the fire station and medical records ({}).", stations);
      List<Person> persons = new ArrayList<>();
      for (String fireStation : stations.split(",")) {
        persons.addAll(floodBusiness.getPersonsLivingNearStation(fireStation));
      }        
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}).", stations);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
      }
      
      TreeMap<String, PersonAndMedicalRecordInFireStation> personsAndMedicalRecordInFireStation = new TreeMap<>();
      for (Person person : persons) {
        var personAndMedicalRecordInFireStation = new PersonAndMedicalRecordInFireStation();
        personAndMedicalRecordInFireStation.setAddress(person.getAddress());
        personAndMedicalRecordInFireStation.setLastName(person.getLastName());
        personAndMedicalRecordInFireStation.setPhoneNumber(person.getPhoneNumber());
        personAndMedicalRecordInFireStation.setAge(person.getAge());

        List<Medication> medications = floodBusiness.getMedicationByName(person.getId());
        personAndMedicalRecordInFireStation.setMedications(medications);
        
        List<Allergy> allergies = floodBusiness.getAllergyByName(person.getId());
        personAndMedicalRecordInFireStation.setAllergies(allergies);
        
        List<FireStation> fireStations = floodBusiness.getFireStationByStationAddress(person.getAddress());
        personAndMedicalRecordInFireStation.setFireStations(fireStations);

        personsAndMedicalRecordInFireStation.put(person.getAddress() + person.getLastName(), personAndMedicalRecordInFireStation);
      }
      LOGGER.info("HTTP GET, SUCCESSFUL ({}).", stations);
      return ResponseEntity.ok(new ArrayList<PersonAndMedicalRecordInFireStation>(personsAndMedicalRecordInFireStation.values()));
    }

}
