package com.safetynet.safetynetalerts.dao.db;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.data.FireStationData;

/**
 * FireStationDaoIT is the integration test class managing the FireStationEntity list
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class FireStationDaoIT {

  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  
  private static FireStationEntity mickFireStationEntity;
  private static Integer mickStationNumber;
  private static String mickAddress;

  @BeforeEach
  private void setUpPerTest() {
    dataBasePrepareService.clearDataBase();
    mickFireStationEntity = FireStationData.getFireStationEntityWallStreet();
    mickStationNumber = mickFireStationEntity.getStation();
    mickAddress = mickFireStationEntity.getAddress();
  }

  // -----------------------------------------------------------------------------------------------
  // Method clearTable
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Clear FireStationEntity list
   */
  @Test
  void clearTable_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    fireStationDao.clearTable();
    // THEN
    assertThat(fireStationDao.fireStationAddressByStationNumber(mickStationNumber)).isEmpty();
  }

  // -----------------------------------------------------------------------------------------------
  // Method fireStationAddressByStationNumber
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, List of addresses served by a fire station
   */
  @Test
  void fireStationAddressByStationNumber_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    List<String> result = fireStationDao.fireStationAddressByStationNumber(mickStationNumber);
    // THEN
    assertThat(result.contains(mickFireStationEntity.getAddress())).isTrue();
  }
  
  /**
   * Borderline case test, empty list, List of addresses served by a fire station
   */
  @Test
  void fireStationAddressByStationNumber_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireStationDao.fireStationAddressByStationNumber(mickStationNumber)).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method fireStationByStationAddress
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, List of fire stations serving an address
   */
  @Test
  void fireStationByStationAddress_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    List<FireStationEntity> result = fireStationDao.fireStationByStationAddress(mickAddress);
    // THEN
    assertThat(result.contains(mickFireStationEntity)).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  /**
   * Borderline case test, empty list, List of fire stations serving an address
   */
  @Test
  void fireStationByStationAddress_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireStationDao.fireStationByStationAddress(mickAddress)).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method fireStationByStationNumberStationAddress
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Search fire station by number and address
   */
  @Test
  void fireStationByStationNumberStationAddress_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    FireStationEntity result = fireStationDao.fireStationByStationNumberStationAddress(
                                 mickStationNumber, mickAddress);
    // THEN
    assertThat(result).isEqualTo(mickFireStationEntity);
  }
  
  /**
   * Borderline case test, empty list, Search fire station by number and address
   */
  @Test
  void fireStationByStationNumberStationAddress_EmptyList() {
    // GIVEN
    // WHEN
    FireStationEntity result = fireStationDao.fireStationByStationNumberStationAddress(
                                                mickStationNumber, mickAddress);
    // THEN
    assertThat(result).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method save
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a fire station
   */
  @Test
  void save_Normal() {
    // GIVEN
    // WHEN
    FireStationEntity result = fireStationDao.save(mickFireStationEntity);
    // THEN
    assertThat(result).isEqualTo(mickFireStationEntity);
    assertThat(result).isEqualTo(fireStationDao.fireStationByStationNumberStationAddress(
                                  mickStationNumber, mickAddress));
  }
  
  /**
   * Borderline case test, record already created, Add a fire station
   */
  @Test
  void save_recordingPresent() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    assertThat(fireStationDao.save(mickFireStationEntity)).isNull();
    // THEN
    assertThat(mickFireStationEntity).isEqualTo(fireStationDao.fireStationByStationNumberStationAddress(
                                                mickStationNumber, mickAddress));
  }

  // -----------------------------------------------------------------------------------------------
  // Method update
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Update an existing fire station
   */
  @Test
  void update_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    
    var newFireStationEntity = new FireStationEntity();
    newFireStationEntity.setStation(FireStationData.getUpdateFireStationWallStreet().getNewStation());
    newFireStationEntity.setAddress(FireStationData.getUpdateFireStationWallStreet().getAddress());
    // WHEN
    FireStationEntity result = fireStationDao.update(mickFireStationEntity, newFireStationEntity);
    // THEN
    assertThat(result).isEqualTo(newFireStationEntity);
    
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(
                              newFireStationEntity.getStation(), newFireStationEntity.getAddress());
    assertThat(result).isEqualTo(readFireStationEntity);
  }
  
  /**
   * Borderline case test, empty list, Update an existing fire station
   */
  @Test
  void update_EmptyList() {
    // GIVEN
    var newFireStationEntity = new FireStationEntity();
    newFireStationEntity.setStation(FireStationData.getUpdateFireStationWallStreet().getNewStation());
    newFireStationEntity.setAddress(FireStationData.getUpdateFireStationWallStreet().getAddress());
    // WHEN
    assertThat(fireStationDao.update(mickFireStationEntity, newFireStationEntity)).isNull();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method delete
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Delete a fire station
   */
  @Test
  void delete_Normal() {
    // GIVEN
    fireStationDao.save(mickFireStationEntity);
    // WHEN
    assertThat(fireStationDao.delete(mickStationNumber, mickAddress)).isTrue();
    // THEN
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(
                                                mickStationNumber, mickAddress);
    assertThat(readFireStationEntity).isNull();
  }
  
  /**
   * Borderline case test, empty list, Delete a fire station
   */
  @Test
  void delete_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(fireStationDao.delete(mickStationNumber, mickAddress)).isFalse();
    // THEN
    FireStationEntity readFireStationEntity = fireStationDao.fireStationByStationNumberStationAddress(
                                                mickStationNumber, mickAddress);
    assertThat(readFireStationEntity).isNull();
  }
}
