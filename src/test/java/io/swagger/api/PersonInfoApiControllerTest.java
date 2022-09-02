package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.FireBusiness;

@WebMvcTest(controllers = PersonInfoApiController.class)
class PersonInfoApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FireBusiness fireBusiness;

  @Test
  void getPersonInfo_returnPersonAndMedicalRecordInFirstNameLastName() throws Exception {
    mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
    .andExpect(status().isOk());
  }

}
