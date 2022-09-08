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
class ChildAlertApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;

  @Test
  void getChildAlert_return200() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/childAlert?address=1509 Culver St")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].child.firstName").value("Roger"))
        .andExpect(jsonPath("$[0].child.lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].child.address").value("1509 Culver St"))
        .andExpect(jsonPath("$[0].child.phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$[0].child.zipCode").value(97451))
        .andExpect(jsonPath("$[0].child.age").value(5))
        .andExpect(jsonPath("$[0].child.city").value("Culver"))
        .andExpect(jsonPath("$[0].child.birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("09/06/2017")))
        .andExpect(jsonPath("$[0].child.email").value("jaboyd@email.com"))
        .andExpect(jsonPath("$[0].familyMembers.[0].firstName").value("Felicia"))
        .andExpect(jsonPath("$[0].familyMembers.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$[0].familyMembers.[0].address").value("1509 Culver St"))
        .andExpect(jsonPath("$[0].familyMembers.[0].phoneNumber").value("841-874-6544"))
        .andExpect(jsonPath("$[0].familyMembers.[0].zipCode").value(97451))
        .andExpect(jsonPath("$[0].familyMembers.[0].age").value(36))
        .andExpect(jsonPath("$[0].familyMembers.[0].city").value("Culver"))
        .andExpect(jsonPath("$[0].familyMembers.[0].birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("01/08/1986")))
        .andExpect(jsonPath("$[0].familyMembers.[0].email").value("jaboyd@email.com"));
    // THEN
  }
  
}
