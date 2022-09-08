package io.swagger.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.FirestationBusiness;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.model.FireStation;
import io.swagger.model.Person;

@WebMvcTest(controllers = FirestationApiController.class)
class FirestationApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FirestationBusiness firestationBusiness;

  // -----------------------------------------------------------------------------------------------
  // Method getFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void getFirestation_return200() throws Exception {
    // GIVEN
    List<Person> persons = new ArrayList<>();
    persons.add(MickBoydData.getPerson());
    when(firestationBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);    
    when(firestationBusiness.getAdultsLivingIn(persons, "3")).thenReturn(8);    
    when(firestationBusiness.getChildrenLivingIn(persons, "3")).thenReturn(3);    
    // WHEN
    mockMvc.perform(get("/firestation?stationNumber=3"))
        .andExpect(status().isOk());
    // THEN
  }
  
  @Test
  void getFirestation_return400() throws Exception {
    // GIVEN
    when(firestationBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(get("/firestation?stationNumber=3"))
        .andExpect(status().isBadRequest());
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method postFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void postFirestation_return201() throws Exception {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    when(firestationBusiness.saveFireStation(any(FireStation.class))).thenReturn(fireStation);    
    // WHEN
    mockMvc.perform(post("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    // THEN
  }
  
  @Test
  void postFirestation_return400() throws Exception {
    // GIVEN
    when(firestationBusiness.saveFireStation(any(FireStation.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(post("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method deleteFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void deleteFirestation_return204() throws Exception {
    // GIVEN
    doNothing().when(firestationBusiness).deleteFireStation(any(String.class), any(String.class));    
    // WHEN
    mockMvc.perform(delete("/firestation?stationNumber=3&address=1234 Wall Street"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  @Test
  void deleteFirestation_return400() throws Exception {
    // GIVEN
    doThrow(new IllegalArgumentException()).when(firestationBusiness).deleteFireStation(any(String.class), any(String.class));
    // WHEN
    mockMvc.perform(delete("/firestation?stationNumber=3&address=1234 Wall Street"))
        .andExpect(status().isBadRequest());
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method putFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void putFirestation_return200() throws Exception {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    when(firestationBusiness.updateFireStation(any(FireStation.class))).thenReturn(fireStation);    
    // WHEN
    mockMvc.perform(put("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    // THEN
  }
  
  @Test
  void putFirestation_return400() throws Exception {
    // GIVEN
    when(firestationBusiness.updateFireStation(any(FireStation.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(put("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }
}
