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
class FireApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;

  @Test
  void getFire_return200() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/fire?address=1509 Culver St")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].person.firstName").value("Felicia"))
        .andExpect(jsonPath("$[0].person.lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].person.address").value("1509 Culver St"))
        .andExpect(jsonPath("$[0].person.phoneNumber").value("841-874-6544"))
        .andExpect(jsonPath("$[0].person.zipCode").value(97451))
        .andExpect(jsonPath("$[0].person.age").value(36))
        .andExpect(jsonPath("$[0].person.city").value("Culver"))
        .andExpect(jsonPath("$[0].person.birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("01/08/1986")))
        .andExpect(jsonPath("$[0].person.email").value("jaboyd@email.com"))
        .andExpect(jsonPath("$[0].medications.[0].medication").value("tetracyclaz:650mg"))
        .andExpect(jsonPath("$[0].allergies.[0].allergy").value("xilliathal"))
        .andExpect(jsonPath("$[0].fireStation.address").value("1509 Culver St"));
    // THEN
  }

}
