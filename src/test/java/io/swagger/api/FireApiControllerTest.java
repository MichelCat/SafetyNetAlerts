package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.FireBusiness;

@WebMvcTest(controllers = FireApiController.class)
public class FireApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FireBusiness fireBusiness;

  @Test
  public void getFire_returnPersonsMedicalFireStationFromAddress() throws Exception {
    mockMvc.perform(get("/fire?address=1509 Culver St"))
    .andExpect(status().isOk());
  }

}
