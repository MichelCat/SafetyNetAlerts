package io.swagger.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.PersonBusiness;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@WebMvcTest(controllers = PersonApiController.class)
class PersonApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonBusiness personBusiness;

  @BeforeEach
  private void setUpPerTest() {
  }

  // -----------------------------------------------------------------------------------------------
  // Method postPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void postPerson_return201() throws Exception {
    // GIVEN
    Person person = MickBoydData.getPerson();
    when(personBusiness.savePerson(any(Person.class))).thenReturn(person);    
    // WHEN
    mockMvc.perform(post("/person")
        .content(MickBoydData.getJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    // THEN
  }
  
  @Test
  void postPerson_return400() throws Exception {
    // GIVEN
    when(personBusiness.savePerson(any(Person.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(post("/person")
        .content(MickBoydData.getJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method deletePerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void deletePerson_return204() throws Exception {
    // GIVEN
    doNothing().when(personBusiness).deletePerson(any(String.class), any(String.class));    
    // WHEN
    mockMvc.perform(delete("/person?firstName=Mick&lastName=Boyd"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  @Test
  void deletePerson_return400() throws Exception {
    // GIVEN
    doThrow(new IllegalArgumentException()).when(personBusiness).deletePerson(any(String.class), any(String.class));
    // WHEN
    mockMvc.perform(delete("/person")
        .param("firstName", "Mick")
        .param("lastName", "Boyd"))
        .andExpect(status().isBadRequest());
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method putPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void putPerson_return200() throws Exception {
    // GIVEN
    Person person = MickBoydData.getPerson();
    when(personBusiness.updatePerson(any(Person.class))).thenReturn(person);    
    // WHEN
    mockMvc.perform(put("/person")
        .content(MickBoydData.getJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    // THEN
  }
  
  @Test
  void putPerson_return400() throws Exception {
    // GIVEN
    when(personBusiness.updatePerson(any(Person.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(put("/person")
        .content(MickBoydData.getJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }
}
