package io.swagger.api;

import io.swagger.business.PersonBusiness;
import io.swagger.model.Person;
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
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PersonApiController implements PersonApi {

    private static final Logger log = LoggerFactory.getLogger(PersonApiController.class);

    private final PersonBusiness personBusiness;

    @Autowired
    public PersonApiController(PersonBusiness personBusiness) {
        this.personBusiness = personBusiness;
    }

    public ResponseEntity<Void> deletePerson(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      try {
        personBusiness.deletePerson(firstName, lastName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
    }

    public ResponseEntity<Person> postPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Person body) {
        Person person = personBusiness.savePerson(body);
        if (person == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(person.getId())
                      .toUri();
        return ResponseEntity.created(location).body(person);
    }

    public ResponseEntity<Person> putPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Person body) {
      Person person = personBusiness.updatePerson(body);
      if (person == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      return ResponseEntity.ok().body(person);
    }

}
