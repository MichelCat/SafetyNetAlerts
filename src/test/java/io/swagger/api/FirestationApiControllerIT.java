package io.swagger.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.data.YoungBoydData;
import io.swagger.utils.DateUtils;

@SpringBootTest
@AutoConfigureMockMvc
class FirestationApiControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private DateUtils dateUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  

  @BeforeAll
  private static void setUp() throws Exception {
  }

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void getFirestation_return200() throws Exception {
    // GIVEN
    fireStationDao.save(FireStationData.getFireStationEntityWallStreet());
    personDao.save(MickBoydData.getPersonEntity());
    personDao.save(YoungBoydData.getPersonEntity());
    // WHEN
    mockMvc.perform(get("/firestation?stationNumber=3")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.adultsCount").value(1))
        .andExpect(jsonPath("$.childrenCount").value(1))
        .andExpect(jsonPath("$.persons.[0].firstName").value("Mick"))
        .andExpect(jsonPath("$.persons.[0].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[0].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$.persons.[0].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$.persons.[0].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[0].age").value(38))
        .andExpect(jsonPath("$.persons.[0].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[0].birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("06/03/1984")))
        .andExpect(jsonPath("$.persons.[0].email").value("jaboyd@email.com"))
        .andExpect(jsonPath("$.persons.[1].firstName").value("Young"))
        .andExpect(jsonPath("$.persons.[1].lastName").value("Boyd"))
        .andExpect(jsonPath("$.persons.[1].address").value("1234 Wall Street"))
        .andExpect(jsonPath("$.persons.[1].phoneNumber").value("841-874-6512"))
        .andExpect(jsonPath("$.persons.[1].zipCode").value(97451))
        .andExpect(jsonPath("$.persons.[1].age").value(9))
        .andExpect(jsonPath("$.persons.[1].city").value("Culver"))
        .andExpect(jsonPath("$.persons.[1].birthdate").value(dateUtils.stringDDMMYYYYToCetConversion("02/18/2012")))
        .andExpect(jsonPath("$.persons.[1].email").value("tenz@email.com"));
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method postFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void postFirestation_return201() throws Exception {
    // GIVEN
    // WHEN
    mockMvc.perform(post("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value("3"))      
        .andExpect(jsonPath("$.address").value("1234 Wall Street"));
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method deleteFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void deleteFirestation_return204() throws Exception {
    // GIVEN
    FireStationEntity fireStationEntity = fireStationDao.save(FireStationData.getFireStationEntityWallStreet());
    // WHEN
    mockMvc.perform(delete("/firestation?stationNumber=3&address=1234 Wall Street")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method putFirestation
  // -----------------------------------------------------------------------------------------------
  @Test
  void putFirestation_return200() throws Exception {
    // GIVEN
    FireStationEntity fireStationEntity = fireStationDao.save(FireStationData.getFireStationEntityWallStreet());
    // WHEN
    mockMvc.perform(put("/firestation")
        .content(FireStationData.getJsonWallStreet())
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("3"))      
        .andExpect(jsonPath("$.address").value("1234 Wall Street"));
    // THEN
  }
}
