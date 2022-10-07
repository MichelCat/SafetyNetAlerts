package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.UpdateFireStation;
import com.safetynet.safetynetalerts.utils.FireStationUtils;

/**
 * FirestationBusinessIT is a class of integration tests on fire stations.
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class FirestationBusinessIT {

  @Autowired
  private FirestationBusiness firestationBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationUtils fireStationUtils;
  
  private static PersonEntity mickPersonEntity;
  private static FireStationEntity mickFireStationEntity;
  private static FireStation mickFireStation;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    mickFireStationEntity = FireStationData.getFireStationEntityWallStreet();
    mickFireStation = FireStationData.getFireStationWallStreet();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Get the list of inhabitants living at the given address
   */
  @Test
  void getPersonsLivingNearStation_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    personDao.save(mickPersonEntity);
    // WHEN
    List<Person> result = firestationBusiness.getPersonsLivingNearStation("1");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, Get the list of inhabitants living at the given address
   */
  @Test
  void getPersonsLivingNearStation_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(firestationBusiness.getPersonsLivingNearStation("1")).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method saveFireStation
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a new fire station
   */
  @Test
  void saveFireStation_Normal() {
    // GIVEN
    // WHEN
    FireStation result = firestationBusiness.saveFireStation(mickFireStation);
    // THEN
    assertThat(result).isEqualTo(mickFireStation);
    
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(
                                                result.getId(), result.getAddress());
    assertThat(result).isEqualTo(
                      fireStationUtils.conversionFireStationEntityToFireStation(readFireStationEntity));
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateFireStation
  // -----------------------------------------------------------------------------------------------
  // General case, Update an existing fire station
  @Test
  void updateFireStation_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
    // WHEN
    FireStation result = firestationBusiness.updateFireStation(updateFireStation);
    // THEN
    FireStation newFireStation = mickFireStation;
    newFireStation.setId(updateFireStation.getNewStation());
    assertThat(result).isEqualTo(newFireStation);

    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(
                                                  result.getId(), result.getAddress());
    assertThat(result).isEqualTo(
                      fireStationUtils.conversionFireStationEntityToFireStation(readFireStationEntity));
  }

  // -----------------------------------------------------------------------------------------------
  // Method deleteFireStation
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Delete an fire station
   */
  @Test
  void deleteFireStation_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    assertThat(firestationBusiness.deleteFireStation("1", "1234 Wall Street")).isTrue();
    // THEN
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(1, "1234 Wall Street");
    assertThat(readFireStationEntity).isNull();
  }
}
