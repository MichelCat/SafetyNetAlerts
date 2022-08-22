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
  public String fireStationAddressByStationNumber(Integer stationNumber) {
    for (FireStationEntity fireStationEntity : safetyNetDataBase.getFireStationEntities()) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        return fireStationEntity.getAddress();
      }
    }
    return "";
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
