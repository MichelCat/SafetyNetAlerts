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
import com.safetynet.safetynetalerts.business.PersonInfoBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

/**
 * PersonInfoApiControllerIT is a class of Endpoint unit tests on people's information.
 * 
 * @author MC
 * @version 1.0
 */
@WebMvcTest(controllers = PersonInfoApiController.class)
class PersonInfoApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonInfoBusiness personInfoBusiness;
  
  private static List<Person> persons;
  private static List<Allergy> allergies;
  private static List<Medication> medications;

  @BeforeEach
  private void setUpPerTest() {
    persons = new ArrayList<>();
    allergies = new ArrayList<>();
    medications = new ArrayList<>();
  }

  /**
   * HTTP GET /personInfo, general case test, return HTTP 200
   */
  @Test
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(personInfoBusiness.getAllPersonsWithTheSameName(any(String.class), any(String.class))).thenReturn(persons);
    allergies.add(MickBoydData.getAllergyNillacilan());
    when(personInfoBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    medications.add(MickBoydData.getMedicationAznol());
    when(personInfoBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").value("Mick"))      
        .andExpect(jsonPath("$[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$[0].age").value("38"))
        .andExpect(jsonPath("$[0].email").value("miboyd@email.com"))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"))
        .andExpect(jsonPath("$", hasSize(1)));
    // THEN
    verify(personInfoBusiness, Mockito.times(1)).getAllPersonsWithTheSameName(any(String.class), any(String.class));
    verify(personInfoBusiness, Mockito.times(1)).getMedicationByName(any(Integer.class));
    verify(personInfoBusiness, Mockito.times(1)).getAllergyByName(any(Integer.class));
  }

  /**
   * HTTP GET /personInfo, borderline case test, empty database, return HTTP 204
   */
  @Test
  void getPersonInfo_return204EmptyList() throws Exception {
    // GIVEN
    when(personInfoBusiness.getAllPersonsWithTheSameName(any(String.class), any(String.class))).thenReturn(persons);
    when(personInfoBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    when(personInfoBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(personInfoBusiness, Mockito.times(1)).getAllPersonsWithTheSameName(any(String.class), any(String.class));
    verify(personInfoBusiness, Mockito.times(0)).getMedicationByName(any(Integer.class));
    verify(personInfoBusiness, Mockito.times(0)).getAllergyByName(any(Integer.class));
  }
}
