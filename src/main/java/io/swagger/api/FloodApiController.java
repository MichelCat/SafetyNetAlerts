package io.swagger.api;

import io.swagger.business.FloodBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInFireStation;
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
import java.util.TreeMap;

@RestController
public class FloodApiController implements FloodApi {

    private static final Logger log = LoggerFactory.getLogger(FloodApiController.class);

    private final FloodBusiness floodBusiness;

    @Autowired
    public FloodApiController(FloodBusiness floodBusiness) {
        this.floodBusiness = floodBusiness;
    }

    public ResponseEntity<List<PersonAndMedicalRecordInFireStation>> getFloodStations(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "stations", required = false) String stations) {
      TreeMap<String, PersonAndMedicalRecordInFireStation> personsAndMedicalRecordInFireStation = new TreeMap<String, PersonAndMedicalRecordInFireStation>();
      
      List<Person> persons = new ArrayList<>();
      for (String fireStation : stations.split(",")) {
        persons.addAll(floodBusiness.getPersonsLivingNearStation(fireStation));
      }        
      
      for (Person person : persons) {
        PersonAndMedicalRecordInFireStation personAndMedicalRecordInFireStation = new PersonAndMedicalRecordInFireStation();
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
      return ResponseEntity.ok(new ArrayList<PersonAndMedicalRecordInFireStation>(personsAndMedicalRecordInFireStation.values()));
    }

}
