package io.swagger.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.ChildAlertBusiness;

@WebMvcTest(controllers = ChildAlertApiController.class)
class ChildAlertApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ChildAlertBusiness childAlertBusiness;

  @Test
  void getChildAlert_returnPersonsFromAddress() throws Exception {
    mockMvc.perform(get("/childAlert?address=1509 Culver St"))
    .andExpect(status().isOk());
  }
}
