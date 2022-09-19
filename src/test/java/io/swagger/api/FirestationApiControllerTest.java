package io.swagger.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
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
import io.swagger.business.FirestationBusiness;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.model.FireStation;
import io.swagger.model.Person;
import io.swagger.model.UpdateFireStation;

@WebMvcTest(controllers = FirestationApiController.class)
class FirestationApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FirestationBusiness firestationBusiness;
  
  private static List<Person> persons;

  @BeforeEach
  private void setUpPerTest() {
    persons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getFirestation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getFirestation_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(firestationBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);    
    when(firestationBusiness.getAdultsLivingIn(persons, "1")).thenReturn(1);    
    when(firestationBusiness.getChildrenLivingIn(persons, "1")).thenReturn(0);    
    // WHEN
    mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(1))
        .andExpect(jsonPath("$.childrenCount").value(0))
        .andExpect(jsonPath("$.persons.[0].firstName").value("Mick"))
        .andExpect(jsonPath("$.persons.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$.persons.[0].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$.persons.[0].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[0].age").value(38))
        .andExpect(jsonPath("$.persons.[0].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[0].email").value("miboyd@email.com"));
    // THEN
    verify(firestationBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
    verify(firestationBusiness, Mockito.times(1)).getAdultsLivingIn(any(List.class), any(String.class));
    verify(firestationBusiness, Mockito.times(1)).getChildrenLivingIn(any(List.class), any(String.class));
  }
  
  // Borderline cases : Empty list
  @Test
  void getFirestation_return200EmptyList() throws Exception {
    // GIVEN
    when(firestationBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);    
    when(firestationBusiness.getAdultsLivingIn(persons, "1")).thenReturn(0);    
    when(firestationBusiness.getChildrenLivingIn(persons, "1")).thenReturn(0);    
    // WHEN
    mockMvc.perform(get("/firestation")
        .param("stationNumber", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(0))
        .andExpect(jsonPath("$.childrenCount").value(0))
        .andExpect(jsonPath("$.persons", hasSize(0)));
    // THEN
    verify(firestationBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
    verify(firestationBusiness, Mockito.times(1)).getAdultsLivingIn(any(List.class), any(String.class));
    verify(firestationBusiness, Mockito.times(1)).getChildrenLivingIn(any(List.class), any(String.class));
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
    mockMvc.perform(delete("/firestation")
        .param("stationNumber", "1")
        .param("address", "1234 Wall Street"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  @Test
  void deleteFirestation_return400() throws Exception {
    // GIVEN
    doThrow(new IllegalArgumentException()).when(firestationBusiness).deleteFireStation(any(String.class), any(String.class));
    // WHEN
    mockMvc.perform(delete("/firestation")
        .param("stationNumber", "1")
        .param("address", "1234 Wall Street"))
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
    when(firestationBusiness.updateFireStation(any(UpdateFireStation.class))).thenReturn(fireStation);    
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
    when(firestationBusiness.updateFireStation(any(UpdateFireStation.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(put("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }
}
