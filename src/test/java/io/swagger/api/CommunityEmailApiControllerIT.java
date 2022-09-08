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
class CommunityEmailApiControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getCommunityEmail_return200() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/communityEmail?city=Culver")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("aly@imail.com"))
        .andExpect(jsonPath("$[*].email").isNotEmpty());
    // THEN
  }

}
