package io.swagger.dao.db;

import java.util.List;
import java.util.SortedSet;
import io.swagger.dao.db.entities.FireStationEntity;

public interface FireStationDao {
  List<String> fireStationAddressByStationNumber(Integer stationNumber);
  FireStationEntity fireStationByStationAddress(String stationAddress);
  FireStationEntity save(FireStationEntity fireStationEntity);
}
