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
class FireApiControllerIT {

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
  void getFire_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    mockMvc.perform(get("/fire")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].person.firstName").value("Mick"))      
        .andExpect(jsonPath("$[0].person.lastName").value("Boyd"))      
        .andExpect(jsonPath("$[0].person.address").value("1234 Wall Street"))      
        .andExpect(jsonPath("$[0].person.phoneNumber").value("841-874-6512"))      
        .andExpect(jsonPath("$[0].person.zipCode").value("97451"))      
        .andExpect(jsonPath("$[0].person.age").value("38"))      
        .andExpect(jsonPath("$[0].person.city").value("Culver"))      
        .andExpect(jsonPath("$[0].person.birthdate").value("03/06/1984"))      
        .andExpect(jsonPath("$[0].person.email").value("miboyd@email.com"))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"))
        .andExpect(jsonPath("$[0].fireStations[0].id").value("1"))
        .andExpect(jsonPath("$[0].fireStations[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$", hasSize(2)));
    // THEN
  }

  // Borderline cases : Empty database
  @Test
  void getFire_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/fire")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
