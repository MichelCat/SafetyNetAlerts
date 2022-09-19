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
class FloodApiControllerIT {

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
  void getFloodStations_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(get("/flood/stations")
        .param("stations", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].address").value("1234 Wall Street"))      
        .andExpect(jsonPath("$[0].lastName").value("Boyd"))      
        .andExpect(jsonPath("$[0].phoneNumber").value("841-874-6512"))      
        .andExpect(jsonPath("$[0].age").value("38"))      
        .andExpect(jsonPath("$[0].fireStations[0].id").value("1"))
        .andExpect(jsonPath("$[0].fireStations[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"))
        .andExpect(jsonPath("$", hasSize(1)));
    // THEN
  }

  // Borderline cases : Empty database
  @Test
  void getFloodStations_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/flood/stations")
        .param("stations", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
