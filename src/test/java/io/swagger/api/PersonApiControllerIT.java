package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.data.MickBoydData;
import io.swagger.utils.DateUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  

  @BeforeAll
  private static void setUp() throws Exception {
  }

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
          .andExpect(jsonPath("$.firstName").value("Mick"))      
          .andExpect(jsonPath("$.lastName").value("Boyd"))      
          .andExpect(jsonPath("$.address").value("1234 Wall Street"))      
          .andExpect(jsonPath("$.phoneNumber").value("841-874-6512"))      
          .andExpect(jsonPath("$.zipCode").value("97451"))      
          .andExpect(jsonPath("$.age").value("38"))      
          .andExpect(jsonPath("$.city").value("Culver"))      
          .andExpect(jsonPath("$.birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("03/06/1984")))      
          .andExpect(jsonPath("$.email").value("jaboyd@email.com"));      
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method deletePerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void deletePerson_return204() throws Exception {
    // GIVEN
    PersonEntity personEntity = personDao.save(MickBoydData.getPersonEntity());
    // WHEN
    mockMvc.perform(delete("/person?firstName=Mick&lastName=Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method putPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  void putPerson_return200() throws Exception {
    // GIVEN
    PersonEntity personEntity = personDao.save(MickBoydData.getPersonEntity());
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
          .andExpect(jsonPath("$.birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("03/06/1984")))      
          .andExpect(jsonPath("$.email").value("jaboyd@email.com"));      
    // THEN
  }

}
