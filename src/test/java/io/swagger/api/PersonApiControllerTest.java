package io.swagger.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.PersonBusiness;
import io.swagger.data.PersonData;
import io.swagger.model.Person;

@WebMvcTest(controllers = PersonApiController.class)
public class PersonApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonBusiness personBusiness;

  @Test
  void postPerson_returnIdPerson() throws Exception {
    // GIVEN
    Person person = PersonData.getPersonJohnBoyd();
    when(personBusiness.save(person)).thenReturn(person);    
    // WHEN
    mockMvc.perform(post("/person")
        .content(PersonData.getJsonJohnBoyd())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

}
