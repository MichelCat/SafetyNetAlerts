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
import com.safetynet.safetynetalerts.model.EmailInCity;

/**
 * CommunityEmailApiControllerIT is a class of Endpoint integration tests on the email addresses of all the inhabitants of the city.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
@AutoConfigureMockMvc
class CommunityEmailApiControllerIT {

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

  /**
   * HTTP GET /communityEmail, general case test, return HTTP 200
   */
  @Test
  void getCommunityEmail_return200() throws Exception {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    MvcResult mvcResult = mockMvc.perform(get("/communityEmail")
        .param("city", "Culver")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[*].email").isNotEmpty())
        .andExpect(jsonPath("$", hasSize(1)))
        .andReturn();
    // THEN
    List<EmailInCity> returnResult = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<EmailInCity>>() { });
        
    List<EmailInCity> emailsInCity = new ArrayList<>();
    var emailInCity = new EmailInCity();
    emailInCity.setEmail(MickBoydData.getPerson().getEmail());
    emailsInCity.add(emailInCity);
    
    assertThat(returnResult).isEqualTo(emailsInCity);
  }

  /**
   * HTTP GET /communityEmail, borderline case test, empty database, return HTTP 204
   */
  @Test
  void getCommunityEmail_return204EmptyDatabase() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/communityEmail")
        .param("city", "Culver")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent())
        .andExpect(jsonPath("$", hasSize(0)));
    // THEN
  }
}
