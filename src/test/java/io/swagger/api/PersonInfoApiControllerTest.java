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
import io.swagger.business.PersonInfoBusiness;
import io.swagger.data.MickBoydData;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.model.Person;

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

  // Borderline cases : Empty list
  @Test
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(personInfoBusiness.getAllPersonsWithTheSameName(any(String.class), any(String.class))).thenReturn(persons);
    allergies.add(MickBoydData.getAllergy());
    when(personInfoBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    medications.add(MickBoydData.getMedication());
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

  // Borderline cases : Empty list
  @Test
  void getPersonInfo_return200EmptyList() throws Exception {
    // GIVEN
    when(personInfoBusiness.getAllPersonsWithTheSameName(any(String.class), any(String.class))).thenReturn(persons);
    when(personInfoBusiness.getAllergyByName(any(Integer.class))).thenReturn(allergies);
    when(personInfoBusiness.getMedicationByName(any(Integer.class))).thenReturn(medications);
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(personInfoBusiness, Mockito.times(1)).getAllPersonsWithTheSameName(any(String.class), any(String.class));
    verify(personInfoBusiness, Mockito.times(0)).getMedicationByName(any(Integer.class));
    verify(personInfoBusiness, Mockito.times(0)).getAllergyByName(any(Integer.class));
  }
}
