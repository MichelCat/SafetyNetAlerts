/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.35). https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package com.safetynet.safetynetalerts.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.safetynet.safetynetalerts.model.ChildLivingInArea;
import com.safetynet.safetynetalerts.model.Error;
import javax.validation.Valid;
import java.util.List;

/**
 * ChildAlertApi is the Endpoint interface that will perform the following actions via Get with HTTP on children.
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public interface ChildAlertApi {

  @Operation(summary = "Get children living in address", description = "", tags = {})
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ChildLivingInArea.class)))),

      @ApiResponse(responseCode = "204", description = "No Content"),

      @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
  @GetMapping(value = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<ChildLivingInArea>> getChildAlert(@Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "address", required = false) String address);

}

