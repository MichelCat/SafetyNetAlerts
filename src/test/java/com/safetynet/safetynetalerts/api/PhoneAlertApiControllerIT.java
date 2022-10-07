package com.safetynet.safetynetalerts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
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
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.PhoneInFireStation;

/**
 * PhoneAlertApiControllerIT is a class of Endpoint integration tests on phone number to alert.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class PhoneAlertApiControllerIT {

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

  /**
   * HTTP GET /phoneAlert, general case test, return HTTP 200
   */
  @Test
  void getPhoneAlert_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    MvcResult mvcResult = mockMvc.perform(get("/phoneAlert")
        .param("firestation", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andReturn();
    // THEN
    List<PhoneInFireStation> returnResult = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PhoneInFireStation>>() { });
    
    List<PhoneInFireStation> phonesInFireStation = new ArrayList<>();
    
    var phoneInFireStation = new PhoneInFireStation();
    phoneInFireStation.setPerson(MickBoydData.getPerson());
    phonesInFireStation.add(phoneInFireStation);
    
    assertThat(returnResult).isEqualTo(phonesInFireStation);
  }

  /**
   * HTTP GET /phoneAlert, borderline case test, empty database, return HTTP 204
   */
  @Test
  void getPhoneAlert_return204EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/phoneAlert")
        .param("firestation", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
