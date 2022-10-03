package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;

@Repository
public class FireStationDaoImpl implements FireStationDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(FireStationDaoImpl.class);

  private static List<FireStationEntity> fireStationEntities = new ArrayList<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    fireStationEntities.clear();
  }
  
  // -----------------------------------------------------------------------------------------------
  @Override
  public List<String> fireStationAddressByStationNumber(Integer stationNumber) {
    LOGGER.debug("Search query by fire station number ({}).", stationNumber);
    List<String> returnList = new ArrayList<>();
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        returnList.add(fireStationEntity.getAddress());
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<FireStationEntity> fireStationByStationAddress(String stationAddress) {
    LOGGER.debug("Search query by fire station address ({}).", stationAddress);
    List<FireStationEntity> returnList = new ArrayList<>();
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)) {
        returnList.add(fireStationEntity);
      }
    }
    return returnList;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public FireStationEntity fireStationByStationNumberStationAddress(Integer stationNumber, String stationAddress) {
    LOGGER.debug("Search query by fire station number and address ({}, {}).", stationNumber, stationAddress);
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(stationNumber)
          && fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)) {
        return fireStationEntity;
      }
    }
    LOGGER.warn("Non-existent fire station ({}, {}).", stationNumber, stationAddress);
    return null;
  }
  
  // -----------------------------------------------------------------------------------------------
  @Override
  public FireStationEntity save(FireStationEntity newFireStationEntity) {
    Integer newStationNumber = newFireStationEntity.getStation();
    String newStationAddress = newFireStationEntity.getAddress();
    
    LOGGER.debug("Query add fire station ({}, {}).", newStationNumber, newStationAddress);
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(newStationNumber)
          && fireStationEntity.getAddress().equalsIgnoreCase(newStationAddress)) {
        LOGGER.warn("Unable to add existing fire station ({}, {}).", newStationNumber, newStationAddress);
        return null;
      }
    }
    if (fireStationEntities.add(newFireStationEntity) == false) {
      LOGGER.error("Unable to add non-existent fire station ({}, {}).", newStationNumber, newStationAddress);
      return null;
    }
    return newFireStationEntity;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public FireStationEntity update(FireStationEntity oldFireStationEntity, FireStationEntity newFireStationEntity) {
    Integer oldStationNumber = oldFireStationEntity.getStation();
    Integer newStationNumber = newFireStationEntity.getStation();
    String stationAddress = oldFireStationEntity.getAddress();
    
    LOGGER.debug("Query update fire station ({}, {}) in ({}, {})."
        , oldStationNumber, stationAddress, newStationNumber, stationAddress);
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(oldStationNumber)
          && fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)) {
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
    }
    LOGGER.warn("Updated a non-existent fire station ({}, {}) in ({}, {})."
        , oldStationNumber, stationAddress, newStationNumber, stationAddress);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public boolean delete(Integer stationNumber, String stationAddress) {
    LOGGER.debug("Query delete fire station ({}, {}).", stationNumber, stationAddress);
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getAddress().equalsIgnoreCase(stationAddress)
          && fireStationEntity.getStation().equals(stationNumber)) {
        if (fireStationEntities.remove(fireStationEntity) == false) {
          LOGGER.error("Unable to delete existing fire station ({}, {}).", stationNumber, stationAddress);
          return false;
        }
        return true;
      }
    }
    LOGGER.warn("Removed a non-existent fire station ({}, {}).", stationNumber, stationAddress);
    return false;
  }
}
