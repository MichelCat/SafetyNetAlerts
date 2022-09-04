package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.business.CommunityEmailBusiness;
import io.swagger.business.FireBusiness;

@WebMvcTest(controllers = CommunityEmailApiController.class)
class CommunityEmailApiControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CommunityEmailBusiness communityEmailBusiness;

  @Test
  void getCommunityEmail_returnEmailFromCity() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(get("/communityEmail?city=Culver"))
        .andExpect(status().isOk());
    // THEN
  }

}
