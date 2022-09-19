package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;
import io.swagger.data.MickBoydData;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordApiControllerIT {

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
  // Method postMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void postMedicalRecord_return201() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(post("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(redirectedUrlPattern("http://*/medicalRecord/1"))
        .andExpect(jsonPath("$.firstName").value("Mick"))      
        .andExpect(jsonPath("$.lastName").value("Boyd"))      
        .andExpect(jsonPath("$.birthdate").value("03/06/1984"))      
        .andExpect(jsonPath("$.medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$.allergies[0].allergy").value("nillacilan"));
    // THEN
  }
  

  // -----------------------------------------------------------------------------------------------
  // Method deleteMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void deleteMedicalRecord_return204() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(delete("/medicalRecord")
        .param("firstName", "Mick")
        .param("lastName", "Boyd"))
        .andExpect(status().isNoContent());
    // THEN
  }

  
  // -----------------------------------------------------------------------------------------------
  // Method putMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void putMedicalRecord_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    mockMvc.perform(put("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName").value("Mick"))      
        .andExpect(jsonPath("$.lastName").value("Boyd"))      
        .andExpect(jsonPath("$.birthdate").value("03/06/1984"))      
        .andExpect(jsonPath("$.medications[0].medication").value("aznol:350mg"))
        .andExpect(jsonPath("$.allergies[0].allergy").value("nillacilan"));
    // THEN
  }
}
