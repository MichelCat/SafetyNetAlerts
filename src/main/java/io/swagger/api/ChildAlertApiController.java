package io.swagger.api;

import io.swagger.business.ChildAlertBusiness;
import io.swagger.model.ChildLivingInArea;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-19T23:12:48.239Z[GMT]")
@RestController
public class ChildAlertApiController implements ChildAlertApi {

  private static final Logger log = LoggerFactory.getLogger(ChildAlertApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  private final ChildAlertBusiness fireStationBusiness;

  @org.springframework.beans.factory.annotation.Autowired
  public ChildAlertApiController(ObjectMapper objectMapper, HttpServletRequest request, ChildAlertBusiness fireStationBusiness) {
    this.objectMapper = objectMapper;
    this.request = request;
    this.fireStationBusiness = fireStationBusiness;
  }

  public ResponseEntity<List<ChildLivingInArea>> getChildAlert(@Parameter(in = ParameterIn.QUERY, description = "", schema = @Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
    List<Person> children = fireStationBusiness.getChildLivingInArea(address);

    List<ChildLivingInArea> childrenLivingInArea = new ArrayList<ChildLivingInArea>();
    for (Person child : children) {
      ChildLivingInArea childLivingInArea = new ChildLivingInArea();
      childLivingInArea.setChild(child);

      List<Person> familyMembers = fireStationBusiness.getOtherHouseholdPersons(
                                          child.getFirstName()
                                          , child.getLastName()
                                          , address);
      childLivingInArea.setFamilyMembers(familyMembers);

      childrenLivingInArea.add(childLivingInArea);
    }
    return ResponseEntity.ok(childrenLivingInArea);
  }

}
