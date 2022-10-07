package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;

/**
 * FireStationDaoImpl manages the FireStationEntity list
 * 
 * @author MC
 * @version 1.0
 */
@Repository
public class FireStationDaoImpl implements FireStationDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(FireStationDaoImpl.class);

  private static List<FireStationEntity> fireStationEntities = new ArrayList<>();

  /**
   * Clear FireStationEntity list
   */
  @Override
  public void clearTable() {
    fireStationEntities.clear();
  }

  /**
   * List of addresses served by a fire station
   * 
   * @param stationNumber Station number
   * @return List of addresses served
   */
  @Override
  public List<String> fireStationAddressByStationNumber(Integer stationNumber) {
    LOGGER.debug("Fire station address search query by fire station number ({}).", stationNumber);
    List<String> returnList = new ArrayList<>();
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        returnList.add(fireStationEntity.getAddress());
      }
    }
    return returnList;
  }

  /**
   * List of fire stations serving an address
   * 
   * @param stationAddress Station address
   * @return List of addresses served
   */
  @Override
  public List<FireStationEntity> fireStationByStationAddress(String stationAddress) {
    LOGGER.debug("Fire station search query by fire station address ({}).", stationAddress);
    List<FireStationEntity> returnList = new ArrayList<>();
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)) {
        returnList.add(fireStationEntity);
      }
    }
    return returnList;
  }

  /**
   * Search fire station by number and address
   * 
   * @param stationNumber Station number
   * @param stationAddress Station address
   * @return FireStationEntity, if successful research, and null if not
   */
  @Override
  public FireStationEntity fireStationByStationNumberStationAddress(Integer stationNumber, String stationAddress) {
    LOGGER.debug("Fire station search query by fire station number and address ({}, {}).", stationNumber, stationAddress);
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(stationNumber) && fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)) {
        return fireStationEntity;
      }
    }
    LOGGER.trace("Non-existent fire station ({}, {}).", stationNumber, stationAddress);
    return null;
  }

  /**
   * Add a fire station
   * 
   * @param newFireStationEntity An object FireStationEntity
   * @return FireStationEntity, successful saved
   */
  @Override
  public FireStationEntity save(FireStationEntity newFireStationEntity) {
    Integer newStationNumber = newFireStationEntity.getStation();
    String newStationAddress = newFireStationEntity.getAddress();

    LOGGER.debug("Query add fire station ({}, {}).", newStationNumber, newStationAddress);

    if (fireStationByStationNumberStationAddress(newStationNumber, newStationAddress) != null) {
      LOGGER.warn("Unable to add existing fire station ({}, {}).", newStationNumber, newStationAddress);
      return null;
    }

    if (fireStationEntities.add(newFireStationEntity) == false) {
      LOGGER.error("Unable to add non-existent fire station ({}, {}).", newStationNumber, newStationAddress);
      return null;
    }
    return newFireStationEntity;
  }

  /**
   * Update an existing fire station
   * 
   * @param oldFireStationEntity An object old FireStationEntity
   * @param newFireStationEntity An object new FireStationEntity
   * @return FireStationEntity, successful updated
   */
  @Override
  public FireStationEntity update(FireStationEntity oldFireStationEntity, FireStationEntity newFireStationEntity) {
    Integer oldStationNumber = oldFireStationEntity.getStation();
    Integer newStationNumber = newFireStationEntity.getStation();
    String stationAddress = oldFireStationEntity.getAddress();

    LOGGER.debug("Query update fire station ({}, {}) in ({}, {}).", oldStationNumber, stationAddress, newStationNumber, stationAddress);

    FireStationEntity fireStationEntity = fireStationByStationNumberStationAddress(oldStationNumber, stationAddress);

    if (fireStationEntity == null) {
      LOGGER.warn("Updated a non-existent fire station ({}, {}) in ({}, {}).", oldStationNumber, stationAddress, newStationNumber, stationAddress);
      return null;
    }

    if (fireStationEntities.remove(fireStationEntity) == false) {
      LOGGER.error("Unable to remove the old fire station ({}, {}).", oldStationNumber, stationAddress);
      return null;
    }
    if (fireStationEntities.add(newFireStationEntity) == false) {
      LOGGER.error("Unable to add new fire station ({}, {}).", newStationNumber, stationAddress);
      return null;
    }
    return newFireStationEntity;
  }

  /**
   * Delete a fire station
   * 
   * @param stationNumber Station Number
   * @param stationAddress Station address
   * @return True, successful deleted
   */
  @Override
  public boolean delete(Integer stationNumber, String stationAddress) {
    LOGGER.debug("Query delete fire station ({}, {}).", stationNumber, stationAddress);

    FireStationEntity fireStationEntity = fireStationByStationNumberStationAddress(stationNumber, stationAddress);

    if (fireStationEntity == null) {
      LOGGER.warn("Removed a non-existent fire station ({}, {}).", stationNumber, stationAddress);
      return false;
    }

    if (fireStationEntities.remove(fireStationEntity) == false) {
      LOGGER.error("Unable to delete existing fire station ({}, {}).", stationNumber, stationAddress);
      return false;
    }
    return true;
  }
}
