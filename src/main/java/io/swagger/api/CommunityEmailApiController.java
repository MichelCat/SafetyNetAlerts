package io.swagger.api;

import io.swagger.business.CommunityEmailBusiness;
import io.swagger.model.EmailInCity;
import io.swagger.model.Person;
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
public class CommunityEmailApiController implements CommunityEmailApi {

    private static final Logger log = LoggerFactory.getLogger(CommunityEmailApiController.class);
    
    private final CommunityEmailBusiness communityEmailBusiness;

    @Autowired
    public CommunityEmailApiController(CommunityEmailBusiness communityEmailBusiness) {
        this.communityEmailBusiness = communityEmailBusiness;
    }

    public ResponseEntity<List<EmailInCity>> getCommunityEmail(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "city", required = false) String city) {
      List<Person> persons = communityEmailBusiness.getPersonByCity(city);
      
      List<EmailInCity> emailsInCity = new ArrayList<>();
      for (Person person : persons) {
        EmailInCity emailInCity = new EmailInCity();
        emailInCity.setEmail(person.getEmail());
        emailsInCity.add(emailInCity);
      }
      return ResponseEntity.ok(emailsInCity);
    }

}
