package com.safetynet.safetynetalerts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.LoadJsonFileInDatabaseBusiness;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.PersonAndMedicalRecordInFirstNameLastName;

/**
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class PersonInfoApiControllerIT {

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

  // General case
  @Test
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    MvcResult mvcResult = mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andReturn();
    // THEN
    List<PersonAndMedicalRecordInFirstNameLastName> returnResult = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<PersonAndMedicalRecordInFirstNameLastName>>() { });
    
    List<PersonAndMedicalRecordInFirstNameLastName> personsAndMedicalRecordInFirstNameLastName = new ArrayList<>();
    
    var person = MickBoydData.getPerson();
    var personAndMedicalRecordInFirstNameLastName = new PersonAndMedicalRecordInFirstNameLastName();
    personAndMedicalRecordInFirstNameLastName.setFirstName(person.getFirstName());
    personAndMedicalRecordInFirstNameLastName.setLastName(person.getLastName());
    personAndMedicalRecordInFirstNameLastName.setAddress(person.getAddress());
    personAndMedicalRecordInFirstNameLastName.setAge(person.getAge());
    personAndMedicalRecordInFirstNameLastName.setEmail(person.getEmail());
    personAndMedicalRecordInFirstNameLastName.addMedicationsItem(MickBoydData.getMedicationAznol());
    personAndMedicalRecordInFirstNameLastName.addAllergiesItem(MickBoydData.getAllergyNillacilan());
    personsAndMedicalRecordInFirstNameLastName.add(personAndMedicalRecordInFirstNameLastName);
    
    person = YoungBoydData.getPerson();
    personAndMedicalRecordInFirstNameLastName = new PersonAndMedicalRecordInFirstNameLastName();
    personAndMedicalRecordInFirstNameLastName.setFirstName(person.getFirstName());
    personAndMedicalRecordInFirstNameLastName.setLastName(person.getLastName());
    personAndMedicalRecordInFirstNameLastName.setAddress(person.getAddress());
    personAndMedicalRecordInFirstNameLastName.setAge(person.getAge());
    personAndMedicalRecordInFirstNameLastName.setEmail(person.getEmail());
    personAndMedicalRecordInFirstNameLastName.addAllergiesItem(YoungBoydData.getAllergyPeanut());
    personsAndMedicalRecordInFirstNameLastName.add(personAndMedicalRecordInFirstNameLastName);
    
    assertThat(returnResult).isEqualTo(personsAndMedicalRecordInFirstNameLastName);
  }

  // Borderline cases : Empty database
  @Test
  void getPersonInfo_return200EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/personInfo")
        .param("firstName", "Mick")
        .param("lastName", "Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
