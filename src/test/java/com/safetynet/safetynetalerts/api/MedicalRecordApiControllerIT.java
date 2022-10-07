package com.safetynet.safetynetalerts.api;

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
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.data.MickBoydData;

/**
 * MedicalRecordApiControllerIT is a class of Endpoint integration tests on medical records.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private MedicationDao medicationDao;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }

  // -----------------------------------------------------------------------------------------------
  // Method postMedicalRecord
  // -----------------------------------------------------------------------------------------------
  /**
   * HTTP POST /medicalRecord, general case test, return HTTP 201
   */
  @Test
  void postMedicalRecord_return201() throws Exception {
    // GIVEN
    allergyDao.save(MickBoydData.getAllergyEntityNillacilan());
    medicationDao.save(MickBoydData.getMedicationEntityAznol());
    personDao.save(MickBoydData.getPersonEntity());
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
  /**
   * HTTP DELETE /medicalRecord, general case test, return HTTP 204
   */
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
  /**
   * HTTP PUT /medicalRecord, general case test, return HTTP 200
   */
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
