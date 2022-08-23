package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.FireStationEntity;

@Repository
public class FireStationDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;
  
  // -----------------------------------------------------------------------------------------------
  public List<String> fireStationAddressByStationNumber(Integer stationNumber) {
    List<String> stationAddresses = new ArrayList<String>();
    for (FireStationEntity fireStationEntity : safetyNetDataBase.getFireStationEntities()) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        stationAddresses.add(fireStationEntity.getAddress());
      }
    }
    return stationAddresses;
  }

  // -----------------------------------------------------------------------------------------------
  public FireStationEntity fireStationByStationAddress(String stationAddress) {
    for (FireStationEntity fireStationEntity : safetyNetDataBase.getFireStationEntities()) {
      if (fireStationEntity.getAddress().equals(stationAddress)) {
        return fireStationEntity;
      }
    }
    return new FireStationEntity();
  }
}
