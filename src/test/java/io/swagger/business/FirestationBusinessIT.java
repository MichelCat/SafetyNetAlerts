package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;
import io.swagger.dao.db.FireStationDao;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.model.FireStation;
import io.swagger.model.Person;
import io.swagger.model.UpdateFireStation;

@SpringBootTest
class FirestationBusinessIT {
  
  private static List<Person> persons;

  @Autowired
  private FirestationBusiness firestationBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
  @Autowired
  private FireStationDao fireStationDao;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    persons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getPersonsLivingNearStation_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    List<Person> result = firestationBusiness.getPersonsLivingNearStation("1");
    // THEN
    persons.add(MickBoydData.getPerson());
    assertThat(result).isEqualTo(persons);
  }
  
  // Borderline cases : Empty list
  @Test
  void getPersonsLivingNearStation_EmptyList() {
    // GIVEN
    // WHEN
    List<Person> result = firestationBusiness.getPersonsLivingNearStation("1");
    // THEN
    assertThat(result).isEqualTo(persons);
  }

  // -----------------------------------------------------------------------------------------------
  // Method saveFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void saveFireStation_Normal() {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    // WHEN
    assertThat(firestationBusiness.saveFireStation(fireStation)).isEqualTo(fireStation);
    // THEN
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(fireStation.getId());
    assertThat(stationAddresses.contains(fireStation.getAddress())).isTrue();
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void updateFireStation_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
    // WHEN
    FireStation result = firestationBusiness.updateFireStation(updateFireStation);
    // THEN
    assertThat(result.getId()).isEqualTo(updateFireStation.getNewStation());
    assertThat(result.getAddress()).isEqualTo(updateFireStation.getAddress());
        
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(updateFireStation.getNewStation());
    assertThat(stationAddresses.contains(updateFireStation.getAddress())).isTrue();
  }

  // -----------------------------------------------------------------------------------------------
  // Method deleteFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void deleteFireStation_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("FireStation.json");
    // WHEN
    firestationBusiness.deleteFireStation("1", "1234 Wall Street");
    // THEN
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(1);
    assertThat(stationAddresses.contains("1234 Wall Street")).isFalse();
  }
}
