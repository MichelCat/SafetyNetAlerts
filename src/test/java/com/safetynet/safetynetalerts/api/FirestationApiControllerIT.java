package com.safetynet.safetynetalerts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.PersonInFireStation;

/**
 * FirestationApiControllerIT is a class of Endpoint integration tests on fire stations.
 * 
 * @author MC
 * @version 1.0
 */
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
  /**
   * HTTP GET /firestation, general case test, return HTTP 200
   */
  @Test
  void getFirestation_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    MvcResult mvcResult = mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.persons", hasSize(2)))
        .andReturn();
    // THEN
    PersonInFireStation returnResult = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<PersonInFireStation>() { });
    
    var personInFireStation = new PersonInFireStation();
    personInFireStation.setAdultsCount(1);
    personInFireStation.setChildrenCount(1);
    personInFireStation.addPersonsItem(MickBoydData.getPerson());
    personInFireStation.addPersonsItem(YoungBoydData.getPerson());
    
    assertThat(returnResult).isEqualTo(personInFireStation);
  }
  
  /**
   * HTTP GET /firestation, borderline case test, empty database, return HTTP 204
   */
  @Test
  void getFirestation_return204EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$.adultsCount").value(0))
        .andExpect(jsonPath("$.childrenCount").value(0))
        .andExpect(jsonPath("$.persons", hasSize(0)));
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method postFirestation
  // -----------------------------------------------------------------------------------------------
  /**
   * HTTP POST /firestation, general case test, return HTTP 201
   */
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
  /**
   * HTTP DELETE /firestation, general case test, return HTTP 204
   */
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
  /**
   * HTTP PUT /firestation, general case test, return HTTP 200
   */
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
