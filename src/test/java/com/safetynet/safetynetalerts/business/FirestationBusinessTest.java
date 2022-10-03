package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.safetynet.safetynetalerts.business.FirestationBusiness;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.FireStationData;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.UpdateFireStation;
import com.safetynet.safetynetalerts.utils.FireStationUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

@SpringBootTest
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
    assertThat(firestationBusiness.getAdultsLivingIn(persons)).isEqualTo(1);
    // THEN
  }

  // Borderline cases : Empty list
  @Test
  void getAdultsLivingIn_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(firestationBusiness.getAdultsLivingIn(persons)).isZero();
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
    assertThat(firestationBusiness.getChildrenLivingIn(persons)).isEqualTo(1);
    // THEN
  }

  // Borderline cases : Empty list
  @Test
  void getChildrenLivingIn_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(firestationBusiness.getChildrenLivingIn(persons)).isZero();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPersonsLivingNearStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getPersonsLivingNearStation_Normal() {
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
    verify(personDao, Mockito.times(1)).findPersonByAddresses(stationAddresses);
    verify(personUtils, Mockito.times(1)).conversionListPersonEntityToPerson(personEntities);
  }

  // Borderline cases : Empty list
  @Test
  void getPersonsLivingNearStation_EmptyList() {
    // GIVEN
    when(fireStationDao.fireStationAddressByStationNumber(any(Integer.class))).thenReturn(stationAddresses);
    when(personDao.findPersonByAddresses(stationAddresses)).thenReturn(personEntities);
    when(personUtils.conversionListPersonEntityToPerson(personEntities)).thenReturn(persons);
    // WHEN
    assertThat(firestationBusiness.getPersonsLivingNearStation("1")).isEqualTo(new ArrayList<>());
    // THEN
    verify(fireStationDao, Mockito.times(1)).fireStationAddressByStationNumber(any(Integer.class));
    verify(personDao, Mockito.times(1)).findPersonByAddresses(stationAddresses);
    verify(personUtils, Mockito.times(1)).conversionListPersonEntityToPerson(personEntities);
  }

  // -----------------------------------------------------------------------------------------------
  // Method saveFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void saveFireStation_Normal() {
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
    verify(fireStationDao, Mockito.times(1)).save(fireStationEntity);
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(fireStationEntity);
  }

  // Borderline cases : Null data
  @Test
  void saveFireStation_NullData() {
    // GIVEN
    FireStation fireStation = FireStationData.getFireStationWallStreet();
    when(fireStationUtils.conversionFireStationToFireStationEntity(fireStation)).thenReturn(null);
    when(fireStationDao.save(null)).thenReturn(null);
    when(fireStationUtils.conversionFireStationEntityToFireStation(null)).thenReturn(null);
    // WHEN
    assertThat(firestationBusiness.saveFireStation(null)).isNull();
    // THEN
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationToFireStationEntity(null);
    verify(fireStationDao, Mockito.times(1)).save(null);
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(null);
  }

  // -----------------------------------------------------------------------------------------------
  // Method updateFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void updateFireStation_Normal() {
    // GIVEN
    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
    
    var oldFireStationEntity = new FireStationEntity();
    oldFireStationEntity.setStation(updateFireStation.getOldStation());
    oldFireStationEntity.setAddress(updateFireStation.getAddress());
    
    FireStationEntity newFireStationEntity = new FireStationEntity();
    newFireStationEntity.setStation(updateFireStation.getNewStation());
    newFireStationEntity.setAddress(updateFireStation.getAddress());

    FireStation fireStation = new FireStation();
    fireStation.setId(updateFireStation.getNewStation());
    fireStation.setAddress(updateFireStation.getAddress());
    
    when(fireStationDao.update(oldFireStationEntity, newFireStationEntity)).thenReturn(newFireStationEntity);
    when(fireStationUtils.conversionFireStationEntityToFireStation(newFireStationEntity)).thenReturn(fireStation);
    // WHEN
    assertThat(firestationBusiness.updateFireStation(updateFireStation)).isEqualTo(fireStation);
    // THEN
  }

  // Borderline cases : Null data
  @Test
  void updateFireStation_NullData() {
    // GIVEN
    UpdateFireStation updateFireStation = FireStationData.getUpdateFireStationWallStreet();
    when(fireStationDao.update(null, null)).thenReturn(null);
    when(fireStationUtils.conversionFireStationEntityToFireStation(null)).thenReturn(null);
    // WHEN
    assertThat(firestationBusiness.updateFireStation(updateFireStation)).isNull(); 
    // THEN
    verify(fireStationUtils, Mockito.times(1)).conversionFireStationEntityToFireStation(null);
 }
  
  // -----------------------------------------------------------------------------------------------
  // Method deleteFireStation
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void deleteFireStation_Normal() {
    // GIVEN
    when(fireStationDao.delete(any(Integer.class), any(String.class))).thenReturn(true);
    // WHEN
    assertThat(firestationBusiness.deleteFireStation("1", "1234 Wall Street")).isTrue();
    // THEN
    verify(fireStationDao, Mockito.times(1)).delete(any(Integer.class), any(String.class));
  }
}
