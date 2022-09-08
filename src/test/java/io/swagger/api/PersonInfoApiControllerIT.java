package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
import io.swagger.data.MickBoydData;
import io.swagger.data.YoungBoydData;

@SpringBootTest
@AutoConfigureMockMvc
class PersonInfoApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
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

  @Test
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    personDao.save(MickBoydData.getPersonEntity());
    personDao.save(YoungBoydData.getPersonEntity());
    // WHEN
    mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].firstName").value("Allison"))
        .andExpect(jsonPath("$[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].address").value("112 Steppes Pl"))
        .andExpect(jsonPath("$[0].age").value(56))
        .andExpect(jsonPath("$[0].email").value("aly@imail.com"))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:200mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"));
    // THEN
  }

}
