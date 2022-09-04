package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.FloodBusiness;

@WebMvcTest(controllers = FloodApiController.class)
class FloodApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FloodBusiness floodBusiness;

  @Test
  void getFloodStations_returnPersonsFromStationThree() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/flood/stations?stations=3"))
        .andExpect(status().isOk());
    // THEN
  }

}
