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
class PersonInfoApiControllerIT {

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
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").value("Mick"))      
        .andExpect(jsonPath("$[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$[0].age").value("38"))
        .andExpect(jsonPath("$[0].email").value("miboyd@email.com"))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"))
        .andExpect(jsonPath("$[1].firstName").value("Young"))
        .andExpect(jsonPath("$[1].lastName").value("Boyd"))
        .andExpect(jsonPath("$[1].address").value("1234 Wall Street"))      
        .andExpect(jsonPath("$[1].age").value("10"))
        .andExpect(jsonPath("$[1].email").value("yoboyd@email.com"))
        .andExpect(jsonPath("$[1].allergies[0].allergy").value("peanut"))
        .andExpect(jsonPath("$", hasSize(2)));
    // THEN
  }

  // Borderline cases : Empty database
  @Test
  void getPersonInfo_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
