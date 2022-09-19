package io.swagger.api;

import io.swagger.business.FireBusiness;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.model.PersonAndMedicalRecordInAddress;
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

@RestController
public class FireApiController implements FireApi {

    private static final Logger log = LoggerFactory.getLogger(FireApiController.class);

    private final FireBusiness fireBusiness;

    @Autowired
    public FireApiController(FireBusiness fireBusiness) {
        this.fireBusiness = fireBusiness;
    }

    public ResponseEntity<List<PersonAndMedicalRecordInAddress>> getFire(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
      List<Person> persons = fireBusiness.getPersonsLivingInAddress(address);
      
      List<PersonAndMedicalRecordInAddress> personsAndMedicalRecordInAddress = new ArrayList<>();
      for (Person person : persons) {
        PersonAndMedicalRecordInAddress personAndMedicalRecordInAddress = new PersonAndMedicalRecordInAddress();
        personAndMedicalRecordInAddress.setPerson(person);

        List<Medication> medications = fireBusiness.getMedicationByName(person.getId());
        personAndMedicalRecordInAddress.setMedications(medications);
        
        List<Allergy> allergies = fireBusiness.getAllergyByName(person.getId());
        personAndMedicalRecordInAddress.setAllergies(allergies);
        
        List<FireStation> fireStations = fireBusiness.getFireStationByStationAddress(person.getAddress());
        personAndMedicalRecordInAddress.setFireStations(fireStations);

        personsAndMedicalRecordInAddress.add(personAndMedicalRecordInAddress);
      }
      return ResponseEntity.ok(personsAndMedicalRecordInAddress);
    }

}
