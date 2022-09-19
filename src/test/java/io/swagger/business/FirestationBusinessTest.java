package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.data.FireStationData;
import io.swagger.data.MickBoydData;
import io.swagger.data.YoungBoydData;
import io.swagger.model.FireStation;
import io.swagger.model.Person;
import io.swagger.model.UpdateFireStation;
import io.swagger.utils.FireStationUtils;
import io.swagger.utils.PersonUtils;

// @SpringBootTest
@WebMvcTest(controllers = FirestationBusiness.class)
class FirestationBusinessTest {

  private static List<String> stationAddresses;
  private static List<PersonEntity> personEntities;
  private static List<Person> persons;

  @Autowired
  private FirestationBusiness firestationBusiness;

  @MockBean
  private PersonDao personDao;
  @MockBean
  private FireStationDao fireStationDao;
  @MockBean
  private PersonUtils personUtils;
  @MockBean
  private FireStationUtils fireStationUtils;

  @BeforeEach
  private void setUpPerTest() {
    stationAddresses = new ArrayList<>();
    personEntities = new ArrayList<>();
    persons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAdultsLivingIn
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAdultsLivingIn_Normal() {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    persons.add(YoungBoydData.getPerson());
    // WHEN
    assertThat(firestationBusiness.getAdultsLivingIn(persons, "1")).isEqualTo(1);
    // THEN
  }

  // Borderline cases : Empty list
  @Test
  void getAdultsLivingIn_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(firestationBusiness.getAdultsLivingIn(persons, "1")).isZero();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getChildrenLivingIn
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getChildrenLivingIn_Normal() {
    // GIVEN
    persons.add(MickBoydData.getPerson());
    persons.add(YoungBoydData.getPerson());
    // WHEN
    assertThat(firestationBusiness.getChildrenLivingIn(persons, "1")).isEqualTo(1);
    // THEN
  }

  // Borderline cases : Empty list
  @Test
  void getChildrenLivingIn_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(firestationBusiness.getChildrenLivingIn(persons, "1")).isZero();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  public void getPersonsLivingNearStation_Normal() {
    // GIVEN
    stationAddresses.add("1234 Wall Street");
    when(fireStationDao.fireStationAddressByStationNumber(1)).thenReturn(stationAddresses);
    personEntities.add(MickBoydData.getPersonEntity());
    when(personDao.findPersonByAddresses(stationAddresses)).thenReturn(personEntities);
    persons.add(MickBoydData.getPerson());
    when(personUtils.conversionListPersonEntityToPerson(personEntities)).thenReturn(persons);
    // WHEN
    assertThat(firestationBusiness.getPersonsLivingNearStation("1")).isEqualTo(persons);
    // THEN
    verify(fireStationDao, Mockito.times(1)).fireStationAddressByStationNumber(any(Integer.class));
    verify(personDao, Mockito.times(1)).findPersonByAddresses(any(List.class));
    verify(personUtils, Mockito.times(1)).conversionListPersonEntityToPerson(any(List.class));
  }

  // Borderline cases : Empty list
  @Test
  public void getPersonsLivingNearStation_EmptyList() {
    // GIVEN
    when(fireStationDao.fireStationAddressByStationNumber(any(Integer.class))).thenReturn(stationAddresses);
    when(personDao.findPersonByAddresses(stationAddresses)).thenReturn(personEntities);
    when(personUtils.conversionListPersonEntityToPerson(personEntities)).thenReturn(persons);
    // WHEN
    assertThat(firestationBusiness.getPersonsLivingNearStation("1")).isEqualTo(new ArrayList<>());
    // THEN
    verify(fireStationDao, Mockito.times(1)).fireStationAddressByStationNumber(any(Integer.class));
    verify(personDao, Mockito.times(1)).findPersonByAddresses(any(List.class));
    verify(personUtils, Mockito.times(1)).conversionListPersonEntityToPerson(any(List.class));
  }

  // -----------------------------------------------------------------------------------------------
  // Method saveFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  public void saveFireStation_Normal() {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    FireStationEntity fireStationEntity = FireStationData.getFireStationEntityWallStreet();
    when(fireStationUtils.conversionFireStationToFireStationEntity(fireStation)).thenReturn(fireStationEntity);
    when(fireStationDao.save(fireStationEntity)).thenReturn(fireStationEntity);
    when(fireStationUtils.conversionFireStationEntityToFireStation(fireStationEntity)).thenReturn(fireStation);
    // WHEN
    assertThat(firestationBusiness.saveFireStation(fireStation)).isEqualTo(fireStation);
    // THEN
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationToFireStationEntity(any(FireStation.class));
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(any(FireStationEntity.class));
  }

  // Borderline cases : Null data
  @Test
  public void saveFireStation_NullData() {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    when(fireStationUtils.conversionFireStationToFireStationEntity(fireStation)).thenReturn(null);
    when(fireStationDao.save(null)).thenReturn(null);
    when(fireStationUtils.conversionFireStationEntityToFireStation(null)).thenReturn(null);
    // WHEN
    assertThat(firestationBusiness.saveFireStation(null)).isNull();
    // THEN
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationToFireStationEntity(null);
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(null);
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateFireStation
  // -----------------------------------------------------------------------------------------------
//  // General case
//  @Test
//  public void updateFireStation_Normal() {
//    // GIVEN
//    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
//    
//    FireStationEntity fireStationEntity = new FireStationEntity();
//    fireStationEntity.setStation(updateFireStation.getNewStation());
//    fireStationEntity.setAddress(updateFireStation.getAddress());
//
//    FireStation fireStation = new FireStation();
//    fireStation.setId(updateFireStation.getNewStation());
//    fireStation.setAddress(updateFireStation.getAddress());
//    
//    doNothing().when(fireStationDao).delete(updateFireStation.getOldStation(), updateFireStation.getAddress());
//    when(fireStationDao.update(fireStationEntity)).thenReturn(fireStationEntity);
//    when(fireStationUtils.conversionFireStationEntityToFireStation(fireStationEntity)).thenReturn(fireStation);
//    // WHEN
//    FireStation result = firestationBusiness.updateFireStation(updateFireStation);
//    assertThat(result).isEqualTo(fireStation);
//    // THEN
//  }

  // Borderline cases : Null data
  @Test
  public void updateFireStation_NullData() {
    // GIVEN
    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
    doNothing().when(fireStationDao).delete(updateFireStation.getOldStation(), updateFireStation.getAddress());
    when(fireStationDao.update(null)).thenReturn(null);
    when(fireStationUtils.conversionFireStationEntityToFireStation(null)).thenReturn(null);
    // WHEN
    FireStation result = firestationBusiness.updateFireStation(updateFireStation); 
    assertThat(result).isNull();
    // THEN
    verify(fireStationDao, Mockito.times(1)).delete(any(Integer.class), any(String.class));
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(null);
 }
  
  // -----------------------------------------------------------------------------------------------
  // Method deleteFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  public void deleteFireStation_Normal() {
    // GIVEN
    doNothing().when(fireStationDao).delete(any(Integer.class), any(String.class));
    // WHEN
    firestationBusiness.deleteFireStation("1", "1234 Wall Street");
    // THEN
    verify(fireStationDao, Mockito.times(1)).delete(any(Integer.class), any(String.class));
  }
}
