package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.PersonInfoBusiness;

@WebMvcTest(controllers = PersonInfoApiController.class)
class PersonInfoApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonInfoBusiness personInfoBusiness;

  @Test
  void getPersonInfo_return200() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/personInfo?firstName=John&lastName=Boyd"))
        .andExpect(status().isOk());
    // THEN
  }

}
