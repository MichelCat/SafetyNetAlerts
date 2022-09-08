package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FloodApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  
  @Test
  void getFloodStations_return200() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/flood/stations?stations=3")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].address").value("112 Steppes Pl"))
        .andExpect(jsonPath("$[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].phoneNumber").value("841-874-9888"))
        .andExpect(jsonPath("$[0].age").value(56))
        .andExpect(jsonPath("$[0].stationNumber").value(3))
        .andExpect(jsonPath("$[0].medications[0].medication").value("aznol:200mg"))
        .andExpect(jsonPath("$[0].allergies[0].allergy").value("nillacilan"));
    // THEN
  }

}
