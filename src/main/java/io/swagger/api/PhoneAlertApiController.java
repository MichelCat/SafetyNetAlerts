package io.swagger.api;

import io.swagger.business.PhoneAlertBusiness;
import io.swagger.model.Person;
import io.swagger.model.PhoneInFireStation;
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
public class PhoneAlertApiController implements PhoneAlertApi {

    private static final Logger log = LoggerFactory.getLogger(PhoneAlertApiController.class);

    private final PhoneAlertBusiness phoneAlertBusiness;

    @Autowired
    public PhoneAlertApiController(PhoneAlertBusiness phoneAlertBusiness) {
        this.phoneAlertBusiness = phoneAlertBusiness;
    }

    public ResponseEntity<List<PhoneInFireStation>> getPhoneAlert(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firestation", required = false) String firestation) {
      List<Person> persons = phoneAlertBusiness.getPersonsLivingNearStation(firestation);
      
      List<PhoneInFireStation> phonesInFireStation = new ArrayList<>();
      for (Person person : persons) {
        PhoneInFireStation phoneInFireStation = new PhoneInFireStation();
        phoneInFireStation.setPerson(person);
        phonesInFireStation.add(phoneInFireStation);
      }
      return ResponseEntity.ok(phonesInFireStation);
    }

}
