package com.safetynet.safetynetalerts.api;

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
import com.safetynet.safetynetalerts.business.FireBusiness;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

/**
 * FireApiControllerTest is a class of Endpoint unit tests on the inhabitants living at the address.
 * 
 * @author MC
 * @version 1.0
 */
@WebMvcTest(controllers = FireApiController.class)
class FireApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FireBusiness fireBusiness;
  
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

  /**
   * HTTP GET /fire, general case test, return HTTP 200
   */
  @Test
  void getFire_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(fireBusiness.getPersonsLivingInAddress(any(String.class))).thenReturn(persons);
    allergies.add(MickBoydData.getAllergyNillacilan());
    when(fireBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    medications.add(MickBoydData.getMedicationAznol());
    when(fireBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    fireStations.add(FireStationData.getFireStationWallStreet());
    when(fireBusiness.getFireStationByStationAddress(any(String.class))).thenReturn(fireStations);
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
        .andExpect(jsonPath("$", hasSize(1)));
    // THEN
    verify(fireBusiness, Mockito.times(1)).getPersonsLivingInAddress(any(String.class));
    verify(fireBusiness, Mockito.times(1)).getMedicationByName(any(Integer.class));
    verify(fireBusiness, Mockito.times(1)).getAllergyByName(any(Integer.class));
    verify(fireBusiness, Mockito.times(1)).getFireStationByStationAddress(any(String.class));
  }

  /**
   * HTTP GET /fire, borderline case test, empty database, return HTTP 204
   */
  @Test
  void getFire_return204EmptyList() throws Exception {
    // GIVEN
    when(fireBusiness.getPersonsLivingInAddress(any(String.class))).thenReturn(persons);
    when(fireBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    when(fireBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    when(fireBusiness.getFireStationByStationAddress(any(String.class))).thenReturn(fireStations);
    // WHEN
    mockMvc.perform(get("/fire")
        .param("address", "1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(fireBusiness, Mockito.times(1)).getPersonsLivingInAddress(any(String.class));
    verify(fireBusiness, Mockito.times(0)).getMedicationByName(any(Integer.class));
    verify(fireBusiness, Mockito.times(0)).getAllergyByName(any(Integer.class));
    verify(fireBusiness, Mockito.times(0)).getFireStationByStationAddress(any(String.class));
  }
}
