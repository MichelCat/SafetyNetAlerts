package io.swagger.utils;

import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.model.FireStation;

@Service
public class FireStationUtils {
  
  public FireStation conversionFireStationEntityToFireStation(FireStationEntity fireStationEntity) {
    FireStation fireStation = new FireStation();
    fireStation.setId(fireStationEntity.getStation());
    fireStation.setAddress(fireStationEntity.getAddress());
    return fireStation;
  }

  public FireStationEntity conversionFireStationToFireStationEntity(FireStation fireStation) {
    FireStationEntity fireStationEntity = new FireStationEntity();
    fireStationEntity.setStation(fireStation.getId());
    fireStationEntity.setAddress(fireStation.getAddress());
    return fireStationEntity;
  }
}
