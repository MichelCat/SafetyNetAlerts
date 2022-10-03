package com.safetynet.safetynetalerts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;

/**
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class PersonApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method postPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void postPerson_return201() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(post("/person")
          .content(MickBoydData.getJson())
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(redirectedUrlPattern("http://*/person/1"))
          .andExpect(jsonPath("$.firstName").value("Mick"))      
          .andExpect(jsonPath("$.lastName").value("Boyd"))      
          .andExpect(jsonPath("$.address").value("1234 Wall Street"))      
          .andExpect(jsonPath("$.phoneNumber").value("841-874-6512"))      
          .andExpect(jsonPath("$.zipCode").value("97451"))      
          .andExpect(jsonPath("$.age").value("38"))      
          .andExpect(jsonPath("$.city").value("Culver"))      
          .andExpect(jsonPath("$.birthdate").value("03/06/1984"))      
          .andExpect(jsonPath("$.email").value("miboyd@email.com"));      
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method deletePerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void deletePerson_return204() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(delete("/person")
        .param("firstName", "Mick")
        .param("lastName", "Boyd"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method putPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void putPerson_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(put("/person")
          .content(MickBoydData.getJson())
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.firstName").value("Mick"))      
          .andExpect(jsonPath("$.lastName").value("Boyd"))      
          .andExpect(jsonPath("$.address").value("1234 Wall Street"))      
          .andExpect(jsonPath("$.phoneNumber").value("841-874-6512"))      
          .andExpect(jsonPath("$.zipCode").value("97451"))      
          .andExpect(jsonPath("$.age").value("38"))      
          .andExpect(jsonPath("$.city").value("Culver"))      
          .andExpect(jsonPath("$.birthdate").value("03/06/1984"))      
          .andExpect(jsonPath("$.email").value("miboyd@email.com"));      
    // THEN
  }

}
