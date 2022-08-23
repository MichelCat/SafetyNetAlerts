package io.swagger.api;

import io.swagger.business.CommunityEmailBusiness;
import io.swagger.model.EmailInCity;
import io.swagger.model.Error;
import io.swagger.model.Person;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-23T16:32:17.084Z[GMT]")
@RestController
public class CommunityEmailApiController implements CommunityEmailApi {

    private static final Logger log = LoggerFactory.getLogger(CommunityEmailApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final CommunityEmailBusiness communityEmailBusiness;

    @org.springframework.beans.factory.annotation.Autowired
    public CommunityEmailApiController(ObjectMapper objectMapper, HttpServletRequest request, CommunityEmailBusiness communityEmailBusiness) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.communityEmailBusiness = communityEmailBusiness;
    }

    public ResponseEntity<List<EmailInCity>> getCommunityEmail(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "city", required = false) String city) {
      List<Person> persons = communityEmailBusiness.getPersonByCity(city);
      
      List<EmailInCity> emailsInCity = new ArrayList<EmailInCity>();
      for (Person person : persons) {
        EmailInCity emailInCity = new EmailInCity();
        emailInCity.setEmail(person.getEmail());
        emailsInCity.add(emailInCity);
      }
      return ResponseEntity.ok(emailsInCity);
    }

}
