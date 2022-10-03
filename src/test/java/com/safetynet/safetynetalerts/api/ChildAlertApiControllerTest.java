package com.safetynet.safetynetalerts.api;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.safetynet.safetynetalerts.api.ChildAlertApiController;
import com.safetynet.safetynetalerts.business.ChildAlertBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.Person;

/**
 * 
 * @author MC
 * @version 1.0
 */
@WebMvcTest(controllers = ChildAlertApiController.class)
class ChildAlertApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChildAlertBusiness childAlertBusiness;
  
  private static List<Person> children;
  private static List<Person> familyMembers;

  @BeforeEach
  private void setUpPerTest() {
    children = new ArrayList<>();
    familyMembers = new ArrayList<>();
  }

  // Borderline cases : Empty list
  @Test
  void getChildAlert_return200() throws Exception {
    // GIVEN
    children.add(YoungBoydData.getPerson());
    when(childAlertBusiness.getChildLivingInArea(any(String.class))).thenReturn(children);
    familyMembers.add(MickBoydData.getPerson());
    when(childAlertBusiness.getOtherHouseholdPersons(any(String.class),any(String.class),any(String.class))).thenReturn(familyMembers);    
    // WHEN
    mockMvc.perform(get("/childAlert")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].child.firstName").value("Young"))
        .andExpect(jsonPath("$[0].child.lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].child.address").value("1234 Wall Street"))
        .andExpect(jsonPath("$[0].child.phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$[0].child.zipCode").value(97451))
        .andExpect(jsonPath("$[0].child.age").value(10))
        .andExpect(jsonPath("$[0].child.city").value("Culver"))
        .andExpect(jsonPath("$[0].child.birthdate").value("18/02/2012"))
        .andExpect(jsonPath("$[0].child.email").value("yoboyd@email.com"))
        .andExpect(jsonPath("$[0].familyMembers.[0].firstName").value("Mick"))
        .andExpect(jsonPath("$[0].familyMembers.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].familyMembers.[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$[0].familyMembers.[0].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$[0].familyMembers.[0].zipCode").value(97451))
        .andExpect(jsonPath("$[0].familyMembers.[0].age").value(38))
        .andExpect(jsonPath("$[0].familyMembers.[0].city").value("Culver"))
        .andExpect(jsonPath("$[0].familyMembers.[0].birthdate").value("03/06/1984"))
        .andExpect(jsonPath("$[0].familyMembers.[0].email").value("miboyd@email.com"))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].familyMembers", hasSize(1)));
    // THEN
    verify(childAlertBusiness, Mockito.times(1)).getChildLivingInArea(any(String.class));
    verify(childAlertBusiness, Mockito.times(1)).getOtherHouseholdPersons(any(String.class), any(String.class), any(String.class));
  }
  
  // Borderline cases : Empty list
  @Test
  void getChildAlert_return200EmptyList() throws Exception {
    // GIVEN
    when(childAlertBusiness.getChildLivingInArea(any(String.class))).thenReturn(children);
    when(childAlertBusiness.getOtherHouseholdPersons(any(String.class),any(String.class),any(String.class))).thenReturn(familyMembers);    
    // WHEN
    mockMvc.perform(get("/childAlert")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(childAlertBusiness, Mockito.times(1)).getChildLivingInArea(any(String.class));
    verify(childAlertBusiness, Mockito.times(0)).getOtherHouseholdPersons(any(String.class), any(String.class), any(String.class));
  }
}
