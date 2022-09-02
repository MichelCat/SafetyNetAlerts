package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.PhoneAlertBusiness;

@WebMvcTest(controllers = PhoneAlertApiController.class)
class PhoneAlertApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PhoneAlertBusiness phoneAlertBusiness;

  @Test
  void getPhoneAlert_returnPersonsFromStationThree() throws Exception {
    mockMvc.perform(get("/phoneAlert?firestation=3")).andExpect(status().isOk());
  }

}
