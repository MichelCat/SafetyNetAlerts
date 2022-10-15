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
import com.safetynet.safetynetalerts.business.CommunityEmailBusiness;
import com.safetynet.safetynetalerts.model.EmailInCity;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * CommunityEmailApiController is the Endpoint will perform the following actions via Get with HTTP the email addresses of all the inhabitants of the city.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class CommunityEmailApiController implements CommunityEmailApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommunityEmailApiController.class);

    @Autowired
    private CommunityEmailBusiness communityEmailBusiness;

    /**
     * Read - Get email addresses of all the inhabitants of the city.
     * 
     * @param city City searched
     * @return List of email addresses 
     */
    public ResponseEntity<List<EmailInCity>> getCommunityEmail(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "city", required = false) String city) {
      LOGGER.debug("HTTP GET, Email addresses of all residents of the city ({}).", city);
      List<Person> persons = communityEmailBusiness.getPersonByCity(city);
      if (persons.isEmpty()) {
        LOGGER.debug("HTTP GET, NO_CONTENT ({}).", city);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
      }
      
      List<EmailInCity> emailsInCity = new ArrayList<>();
      for (Person person : persons) {
        var emailInCity = new EmailInCity();
        emailInCity.setEmail(person.getEmail());
        emailsInCity.add(emailInCity);
      }
      LOGGER.info("HTTP GET, SUCCESSFUL ({}).", city);
      return ResponseEntity.ok(emailsInCity);
    }

}
