package com.safetynet.safetynetalerts.dao.db;

import java.util.List;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;

/**
 * FireStationDaois interface that manages the FireStationEntity list
 * 
 * @author MC
 * @version 1.0
 */
public interface FireStationDao {
  void clearTable();
  List<String> fireStationAddressByStationNumber(Integer stationNumber);
  List<FireStationEntity> fireStationByStationAddress(String stationAddress);
  FireStationEntity fireStationByStationNumberStationAddress(Integer stationNumber, String stationAddress);
  FireStationEntity save(FireStationEntity fireStationEntity);
  FireStationEntity update(FireStationEntity oldFireStationEntity, FireStationEntity newFireStationEntity);
  boolean delete(Integer stationNumber, String stationAddress);
}
