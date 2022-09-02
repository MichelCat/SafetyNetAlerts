package io.swagger.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import io.swagger.data.PersonData;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  
  @Test
  void postPerson_returnIdPerson() throws Exception {
      mockMvc.perform( MockMvcRequestBuilders
          .post("/person")
          .content(PersonData.getJsonJohnBoyd())
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Boyd"));      
  }

}
