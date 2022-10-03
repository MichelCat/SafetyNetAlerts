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
import com.safetynet.safetynetalerts.api.MedicalRecordApiController;
import com.safetynet.safetynetalerts.business.MedicalRecordBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.MedicalRecord;

/**
 * 
 * @author MC
 * @version 1.0
 */
@WebMvcTest(controllers = MedicalRecordApiController.class)
class MedicalRecordApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MedicalRecordBusiness medicalRecordBusiness;

  @BeforeEach
  private void setUpPerTest() {
  }

  // -----------------------------------------------------------------------------------------------
  // Method postMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void postMedicalRecord_return201() throws Exception {
    // GIVEN
    MedicalRecord medicalRecord = MickBoydData.getMedicalRecord();
    when(medicalRecordBusiness.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);    
    // WHEN
    mockMvc.perform(post("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    // THEN
  }
  
  @Test
  void postMedicalRecord_return400() throws Exception {
    // GIVEN
    when(medicalRecordBusiness.saveMedicalRecord(any(MedicalRecord.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(post("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }


  // -----------------------------------------------------------------------------------------------
  // Method deleteMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void deleteMedicalRecord_return204() throws Exception {
    // GIVEN
    when(medicalRecordBusiness.deleteMedicalRecord(any(String.class), any(String.class))).thenReturn(true);
    // WHEN
    mockMvc.perform(delete("/medicalRecord")
        .param("firstName", "Mick")
        .param("lastName", "Boyd"))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  @Test
  void deleteMedicalRecord_return400() throws Exception {
    // GIVEN
    when(medicalRecordBusiness.deleteMedicalRecord(any(String.class), any(String.class))).thenReturn(false);
    // WHEN
    mockMvc.perform(delete("/medicalRecord")
        .param("firstName", "Mick")
        .param("lastName", "Boyd"))
        .andExpect(status().isBadRequest());
    // THEN
  }

  
  // -----------------------------------------------------------------------------------------------
  // Method putMedicalRecord
  // -----------------------------------------------------------------------------------------------
  @Test
  void putMedicalRecord_return200() throws Exception {
    // GIVEN
    MedicalRecord medicalRecord = MickBoydData.getMedicalRecord();
    when(medicalRecordBusiness.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(medicalRecord);    
    // WHEN
    mockMvc.perform(put("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
    // THEN
  }
  
  @Test
  void putMedicalRecord_return400() throws Exception {
    // GIVEN
    when(medicalRecordBusiness.updateMedicalRecord(any(MedicalRecord.class))).thenReturn(null);    
    // WHEN
    mockMvc.perform(put("/medicalRecord")
        .content(MickBoydData.getMedicalRecordJson())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    // THEN
  }
}
