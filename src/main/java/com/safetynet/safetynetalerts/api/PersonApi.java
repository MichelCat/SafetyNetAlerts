/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35). https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package com.safetynet.safetynetalerts.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.safetynet.safetynetalerts.model.Error;
import com.safetynet.safetynetalerts.model.Person;
import javax.validation.Valid;

/**
 * PersonApi is the Endpoint interface that will perform the following actions via Post/Put/Delete with HTTP on person.
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public interface PersonApi {

  @Operation(summary = "Delete a person", description = "", tags = {})
  @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),

      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
  @DeleteMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Void> deletePerson(@Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "firstName", required = false) String firstName, @Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "lastName", required = false) String lastName);


  @Operation(summary = "Add a new person", description = "", tags = {})
  @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))),

      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
  @PostMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Person> postPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody Person body);


  @Operation(summary = "Update an existing person", description = "", tags = {})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))),

      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
  @PutMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Person> putPerson(@Parameter(in = ParameterIn.DEFAULT, description = "", schema = @Schema()) @Valid @RequestBody Person body);

}

