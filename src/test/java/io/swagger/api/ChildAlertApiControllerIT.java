package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;

@SpringBootTest
@AutoConfigureMockMvc
class ChildAlertApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }

  // General case
  @Test
  void getChildAlert_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
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
  }
  
  // Borderline cases : Empty database
  @Test
  void getChildAlert_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/childAlert")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
