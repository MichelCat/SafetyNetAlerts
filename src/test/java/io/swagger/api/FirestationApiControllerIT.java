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
import io.swagger.utils.DateUtils;

@SpringBootTest
@AutoConfigureMockMvc
class FirestationApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;
  
  @Test
  void getFirestation_returnPersonsFromStationThree() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/firestation?stationNumber=3")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(8))
        .andExpect(jsonPath("$.childrenCount").value(3))
        .andExpect(jsonPath("$.persons.[0].firstName").value("Allison"))
        .andExpect(jsonPath("$.persons.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[0].address").value("112 Steppes Pl"))
        .andExpect(jsonPath("$.persons.[0].phoneNumber").value("841-874-9888"))
        .andExpect(jsonPath("$.persons.[0].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[0].age").value(56))
        .andExpect(jsonPath("$.persons.[0].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[0].birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("03/03/1966")))
        .andExpect(jsonPath("$.persons.[0].email").value("aly@imail.com"));
    // THEN
  }
  
}
