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
class PhoneAlertApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;

  @Test
  void getPhoneAlert_returnPersonsFromStationThree() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/phoneAlert?firestation=3")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].person.firstName").value("Allison"))
        .andExpect(jsonPath("$[0].person.lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].person.address").value("112 Steppes Pl"))
        .andExpect(jsonPath("$[0].person.phoneNumber").value("841-874-9888"))
        .andExpect(jsonPath("$[0].person.zipCode").value(97451))
        .andExpect(jsonPath("$[0].person.age").value(56))
        .andExpect(jsonPath("$[0].person.city").value("Culver"))
        .andExpect(jsonPath("$[0].person.birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("03/03/1966")))
        .andExpect(jsonPath("$[0].person.email").value("aly@imail.com"));
    // THEN
  }

}
