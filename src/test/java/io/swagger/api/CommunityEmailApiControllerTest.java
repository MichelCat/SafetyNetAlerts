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
import io.swagger.business.CommunityEmailBusiness;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@WebMvcTest(controllers = CommunityEmailApiController.class)
class CommunityEmailApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CommunityEmailBusiness communityEmailBusiness;
  
  private static List<Person> persons;

  @BeforeEach
  private void setUpPerTest() {
    persons = new ArrayList<>();
  }

  // Borderline cases : Empty list
  @Test
  void getCommunityEmail_return200() throws Exception {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    when(communityEmailBusiness.getPersonByCity(any(String.class))).thenReturn(persons);    
    // WHEN
    mockMvc.perform(get("/communityEmail")
        .param("city", "Culver")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("miboyd@email.com"))
        .andExpect(jsonPath("$[*].email").isNotEmpty())
        .andExpect(jsonPath("$", hasSize(1)));
    // THEN
    verify(communityEmailBusiness, Mockito.times(1)).getPersonByCity(any(String.class));
  }

  // Borderline cases : Empty list
  @Test
  void getCommunityEmail_returnEmptyList200() throws Exception {
    // GIVEN
    when(communityEmailBusiness.getPersonByCity(any(String.class))).thenReturn(persons);    
    // WHEN
    mockMvc.perform(get("/communityEmail")
        .param("city", "Culver")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
    verify(communityEmailBusiness, Mockito.times(1)).getPersonByCity(any(String.class));
  }
}
