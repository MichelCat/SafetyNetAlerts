package com.safetynet.safetynetalerts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
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
import com.safetynet.safetynetalerts.model.PersonAndMedicalRecordInFireStation;

/**
 * 
 * @author MC
 * @version 1.0
 */
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
    MvcResult mvcResult = mockMvc.perform(get("/flood/stations")
        .param("stations", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andReturn();
    // THEN
    List<PersonAndMedicalRecordInFireStation> returnResult = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PersonAndMedicalRecordInFireStation>>() { });
    
    TreeMap<String, PersonAndMedicalRecordInFireStation> personsAndMedicalRecordInFireStation = new TreeMap<String, PersonAndMedicalRecordInFireStation>();
   
    var personAndMedicalRecordInFireStation = new PersonAndMedicalRecordInFireStation();
    personAndMedicalRecordInFireStation.setAddress(MickBoydData.getPerson().getAddress());
    personAndMedicalRecordInFireStation.setLastName(MickBoydData.getPerson().getLastName());
    personAndMedicalRecordInFireStation.setPhoneNumber(MickBoydData.getPerson().getPhoneNumber());
    personAndMedicalRecordInFireStation.setAge(MickBoydData.getPerson().getAge());
    personAndMedicalRecordInFireStation.addMedicationsItem(MickBoydData.getMedicationAznol());
    personAndMedicalRecordInFireStation.addAllergiesItem(MickBoydData.getAllergyNillacilan());
    personAndMedicalRecordInFireStation.addFireStationsItem(FireStationData.getFireStationWallStreet());
    personsAndMedicalRecordInFireStation.put(MickBoydData.getPerson().getAddress() + MickBoydData.getPerson().getLastName()
                              , personAndMedicalRecordInFireStation);
    
    assertThat(returnResult).isEqualTo(new ArrayList<PersonAndMedicalRecordInFireStation>(personsAndMedicalRecordInFireStation.values()));
  }

  // Borderline cases : Empty database
  @Test
  void getFloodStations_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/flood/stations")
        .param("stations", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
