package io.swagger.api;

import io.swagger.business.ChildAlertBusiness;
import io.swagger.model.ChildLivingInArea;
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
public class ChildAlertApiController implements ChildAlertApi {

    private static final Logger log = LoggerFactory.getLogger(ChildAlertApiController.class);

    private final ChildAlertBusiness childAlertBusiness;

    @Autowired
    public ChildAlertApiController(ChildAlertBusiness childAlertBusiness) {
        this.childAlertBusiness = childAlertBusiness;
    }

    public ResponseEntity<List<ChildLivingInArea>> getChildAlert(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "address", required = false) String address) {
    List<Person> children = childAlertBusiness.getChildLivingInArea(address);

    List<ChildLivingInArea> childrenLivingInArea = new ArrayList<>();
    for (Person child : children) {
      ChildLivingInArea childLivingInArea = new ChildLivingInArea();
      childLivingInArea.setChild(child);

      List<Person> familyMembers = childAlertBusiness.getOtherHouseholdPersons(
                                          child.getFirstName()
                                          , child.getLastName()
                                          , address);
      childLivingInArea.setFamilyMembers(familyMembers);

      childrenLivingInArea.add(childLivingInArea);
    }
    return ResponseEntity.ok(childrenLivingInArea);
    }

}
