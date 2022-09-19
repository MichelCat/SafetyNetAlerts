package io.swagger.api;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import io.swagger.business.FloodBusiness;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;

@WebMvcTest(controllers = FloodApiController.class)
class FloodApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FloodBusiness floodBusiness;
  
  private static List<Person> persons;
  private static List<Allergy> allergies;
  private static List<Medication> medications;
  private static List<FireStation> fireStations;

  @BeforeEach
  private void setUpPerTest() {
    persons = new ArrayList<>();
    allergies = new ArrayList<>();
    medications = new ArrayList<>();
    fireStations = new ArrayList<>();
  }

  // Borderline cases : Empty list
  @Test
  void getFloodStations_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(floodBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);
    allergies.add(MickBoydData.getAllergy());
    when(floodBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    medications.add(MickBoydData.getMedication());
    when(floodBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    fireStations.add(FireStationData.getFireStationWallStreet());
    when(floodBusiness.getFireStationByStationAddress(any(String.class))).thenReturn(fireStations);
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
    verify(floodBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
    verify(floodBusiness, Mockito.times(1)).getMedicationByName(any(Integer.class));
    verify(floodBusiness, Mockito.times(1)).getAllergyByName(any(Integer.class));
    verify(floodBusiness, Mockito.times(1)).getFireStationByStationAddress(any(String.class));
  }

  // Borderline cases : Empty list
  @Test
  void getFloodStations_return200EmptyList() throws Exception {
    // GIVEN
    when(floodBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);
    when(floodBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    when(floodBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    when(floodBusiness.getFireStationByStationAddress(any(String.class))).thenReturn(fireStations);
    // WHEN
    mockMvc.perform(get("/flood/stations")
        .param("stations", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(floodBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
    verify(floodBusiness, Mockito.times(0)).getMedicationByName(any(Integer.class));
    verify(floodBusiness, Mockito.times(0)).getAllergyByName(any(Integer.class));
    verify(floodBusiness, Mockito.times(0)).getFireStationByStationAddress(any(String.class));
  }
}
