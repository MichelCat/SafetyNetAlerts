package io.swagger.dao.db;

import java.util.List;
import io.swagger.dao.db.entities.FireStationEntity;

public interface FireStationDao {
  void clearTable();
  List<String> fireStationAddressByStationNumber(Integer stationNumber);
  List<FireStationEntity> fireStationByStationAddress(String stationAddress);
  FireStationEntity save(FireStationEntity fireStationEntity);
  FireStationEntity update(FireStationEntity fireStationEntity);
  void delete(Integer stationNumber, String stationAddress);
}
