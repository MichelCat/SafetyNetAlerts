package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.FireStationEntity;

@Repository
public class FireStationDaoImpl implements FireStationDao {

  private static SortedSet<FireStationEntity> fireStationEntities = new TreeSet<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<String> fireStationAddressByStationNumber(Integer stationNumber) {
    List<String> stationAddresses = new ArrayList<>();
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        stationAddresses.add(fireStationEntity.getAddress());
      }
    }
    return stationAddresses;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public FireStationEntity fireStationByStationAddress(String stationAddress) {
    for (FireStationEntity fireStationEntity : fireStationEntities) {
      if (fireStationEntity.getAddress().equals(stationAddress)) {
        return fireStationEntity;
      }
    }
    return new FireStationEntity();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public FireStationEntity save(FireStationEntity fireStationEntity) {
    fireStationEntities.add(fireStationEntity);
    return fireStationEntity;
  }

}
