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
import com.safetynet.safetynetalerts.business.ChildAlertBusiness;
import com.safetynet.safetynetalerts.model.ChildLivingInArea;
import com.safetynet.safetynetalerts.model.Person;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * ChildAlertApiController is the Endpoint class that will perform Gets with HTTP on children.
 * @author MC
 * @version 1.0
 */
@RestController
public class ChildAlertApiController implements ChildAlertApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChildAlertApiController.class);

    @Autowired
    private ChildAlertBusiness childAlertBusiness;

    /***
     * Read - Get a list of children (any individual aged 18 or younger) living at this address.
     * The list includes each child's first and last name, age, and a list of other household members.
     * @param address Child's address
     * @return List of children
     */
    public ResponseEntity<List<ChildLivingInArea>> getChildAlert(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
    LOGGER.debug("HTTP GET, List of children living at this address ({}).", address);
    List<Person> children = childAlertBusiness.getChildLivingInArea(address);
    if (children.isEmpty()) {
      LOGGER.debug("HTTP GET, NO_CONTENT ({}).", address);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
    }

    List<ChildLivingInArea> childrenLivingInArea = new ArrayList<>();
    for (Person child : children) {
      var childLivingInArea = new ChildLivingInArea();
      childLivingInArea.setChild(child);

      List<Person> familyMembers = childAlertBusiness.getOtherHouseholdPersons(
                                          child.getFirstName()
                                          , child.getLastName()
                                          , address);
      childLivingInArea.setFamilyMembers(familyMembers);

      childrenLivingInArea.add(childLivingInArea);
    }
    LOGGER.debug("HTTP GET, SUCCESSFUL ({}).", address);
    return ResponseEntity.ok(childrenLivingInArea);
    }

}
