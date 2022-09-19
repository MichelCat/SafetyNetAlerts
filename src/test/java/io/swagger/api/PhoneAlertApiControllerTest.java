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
import io.swagger.business.PhoneAlertBusiness;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@WebMvcTest(controllers = PhoneAlertApiController.class)
class PhoneAlertApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PhoneAlertBusiness phoneAlertBusiness;
  
  private static List<Person> persons;

  @BeforeEach
  private void setUpPerTest() {
    persons = new ArrayList<>();
  }

  // Borderline cases : Empty list
  @Test
  void getPhoneAlert_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(phoneAlertBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);
    // WHEN
    mockMvc.perform(get("/phoneAlert")
        .param("firestation", "1")
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
        .andExpect(jsonPath("$", hasSize(1)));
    // THEN
    verify(phoneAlertBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
  }

  // Borderline cases : Empty list
  @Test
  void getPhoneAlert_return200EmptyList() throws Exception {
    // GIVEN
    when(phoneAlertBusiness.getPersonsLivingNearStation(any(String.class))).thenReturn(persons);
    // WHEN
    mockMvc.perform(get("/phoneAlert")
        .param("firestation", "1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(phoneAlertBusiness, Mockito.times(1)).getPersonsLivingNearStation(any(String.class));
  }
}
