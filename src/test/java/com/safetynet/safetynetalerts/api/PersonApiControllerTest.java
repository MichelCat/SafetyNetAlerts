package com.safetynet.safetynetalerts.api;

import static org.mockito.ArgumentMatchers.any;
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
import com.safetynet.safetynetalerts.business.PersonBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Person;

/**
 * PersonApiControllerTest is a class of Endpoint unit tests on person.
 * 
 * @author MC
 * @version 1.0
 */
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
  /**
   * HTTP POST /person, general case test, return HTTP 201
   */
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
  
  /**
   * HTTP POST /person, borderline case test, existing person, return HTTP 400
   */
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
  /**
   * HTTP DELETE /person, general case test, return HTTP 204
   */
  @Test
  void deletePerson_return204() throws Exception {
    // GIVEN
    when(personBusiness.deletePerson(any(String.class), any(String.class))).thenReturn(true);    
    // WHEN
    mockMvc.perform(delete("/person?firstName=Mick&lastName=Boyd"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  /**
   * HTTP DELETE /person, borderline case test, non-existent person, return HTTP 400
   */
  @Test
  void deletePerson_return400() throws Exception {
    // GIVEN
    when(personBusiness.deletePerson(any(String.class), any(String.class))).thenReturn(false);
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
  /**
   * HTTP PUT /person, general case test, return HTTP 200
   */
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
  
  /**
   * HTTP PUT /person, borderline case test, non-existent person, return HTTP 400
   */
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
