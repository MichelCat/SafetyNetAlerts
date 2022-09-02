package io.swagger.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FireApiControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getFire_returnPersonsMedicalFireStationFromAddress() throws Exception {
      mockMvc.perform(get("/fire?address=1509 Culver St"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].person.firstName", is("Felicia")));
  }

}
