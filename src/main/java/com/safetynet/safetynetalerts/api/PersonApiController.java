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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.safetynet.safetynetalerts.business.PersonBusiness;
import com.safetynet.safetynetalerts.model.Person;
import javax.validation.Valid;
import java.net.URI;

/**
 * PersonApiController is the Endpoint will perform the following actions via Post/Put/Delete with HTTP on person.
 * 
 * @author MC
 * @version 1.0
 */
@RestController
public class PersonApiController implements PersonApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonApiController.class);

    @Autowired
    private PersonBusiness personBusiness;

    /**
     * Create - Add a person
     * 
     * @param body An object person
     * @return The person object saved
     */
    public ResponseEntity<Person> postPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Person body) {
      LOGGER.debug("HTTP POST, Add a person ({}, {}).", body.getFirstName(), body.getLastName());
        Person person = personBusiness.savePerson(body);
        if (person == null) {
          LOGGER.debug("HTTP POST, BAD_REQUEST ({}, {}).", body.getFirstName(), body.getLastName());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        URI location = ServletUriComponentsBuilder
                      .fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(person.getId())
                      .toUri();
        LOGGER.info("HTTP POST, CREATED ({}, {}).", body.getFirstName(), body.getLastName());
        return ResponseEntity.created(location).body(person);
    }

    /**
     * Update - Update an existing person
     * 
     * @param body An object person
     * @return The person object updated
     */
    public ResponseEntity<Person> putPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Person body) {
      LOGGER.debug("HTTP PUT, Update person ({}, {}).", body.getFirstName(), body.getLastName());
      Person person = personBusiness.updatePerson(body);
      if (person == null) {
        LOGGER.debug("HTTP PUT, BAD_REQUEST ({}, {}).", body.getFirstName(), body.getLastName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.info("HTTP PUT, SUCCESSFUL ({}, {}).", body.getFirstName(), body.getLastName());
      return ResponseEntity.ok().body(person);
    }

    /**
     * Delete - Delete an person
     * 
     * @param firstName - The first name of the person to delete
     * @param lastName - The last name of the person to delete
     */
    public ResponseEntity<Void> deletePerson(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName,@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName) {
      LOGGER.debug("HTTP DELETE, Delete a person ({}, {}).", firstName, lastName);
      if (personBusiness.deletePerson(firstName, lastName) == false) {
        LOGGER.debug("HTTP DELETE, BAD_REQUEST ({}, {}).", firstName, lastName);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
      }
      LOGGER.info("HTTP DELETE, NO_CONTENT ({}, {}).", firstName, lastName);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
