package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
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
import io.swagger.data.FireStationData;

@SpringBootTest
@AutoConfigureMockMvc
class FirestationApiControllerIT {

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
  
  // -----------------------------------------------------------------------------------------------
  // Method getFirestation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getFirestation_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(1))
        .andExpect(jsonPath("$.childrenCount").value(1))
        .andExpect(jsonPath("$.persons.[0].firstName").value("Mick"))
        .andExpect(jsonPath("$.persons.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$.persons.[0].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$.persons.[0].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[0].age").value(38))
        .andExpect(jsonPath("$.persons.[0].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[0].birthdate").value("03/06/1984"))
        .andExpect(jsonPath("$.persons.[0].email").value("miboyd@email.com"))
        .andExpect(jsonPath("$.persons.[1].firstName").value("Young"))
        .andExpect(jsonPath("$.persons.[1].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[1].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$.persons.[1].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$.persons.[1].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[1].age").value(10))
        .andExpect(jsonPath("$.persons.[1].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[1].birthdate").value("18/02/2012"))
        .andExpect(jsonPath("$.persons.[1].email").value("yoboyd@email.com"))
        .andExpect(jsonPath("$.persons", hasSize(2)));
    // THEN
  }
  
  //Borderline cases : Empty database
  @Test
  void getFirestation_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(0))
        .andExpect(jsonPath("$.childrenCount").value(0))
        .andExpect(jsonPath("$.persons", hasSize(0)));
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method postFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void postFirestation_return201() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(post("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(redirectedUrlPattern("http://*/firestation/1"))
        .andExpect(jsonPath("$.id").value("1"))      
        .andExpect(jsonPath("$.address").value("1234 Wall Street"));
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method deleteFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void deleteFirestation_return204() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    // WHEN
    mockMvc.perform(delete("/firestation")
        .param("stationNumber", "1")
        .param("address", "1234 Wall Street"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method putFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void putFirestation_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    // WHEN
    mockMvc.perform(put("/firestation")
        .content(FireStationData.getJsonUpdateWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("9"))      
        .andExpect(jsonPath("$.address").value("1234 Wall Street"));
    // THEN
  }
}
