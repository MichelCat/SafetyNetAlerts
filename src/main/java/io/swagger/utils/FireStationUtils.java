package io.swagger.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.model.FireStation;

@Service
public class FireStationUtils {

  public List<FireStation> conversionListFireStationEntityToFireStation(List<FireStationEntity> fireStationEntities) {
    List<FireStation> fireStations = new ArrayList<>();
    fireStationEntities.forEach(e -> {
      fireStations.add(conversionFireStationEntityToFireStation(e));
    });
    return fireStations;
  }
  
  public FireStation conversionFireStationEntityToFireStation(FireStationEntity fireStationEntity) {
    var fireStation = new FireStation();
    fireStation.setId(fireStationEntity.getStation());
    fireStation.setAddress(fireStationEntity.getAddress());
    return fireStation;
  }

  public FireStationEntity conversionFireStationToFireStationEntity(FireStation fireStation) {
    var fireStationEntity = new FireStationEntity();
    fireStationEntity.setStation(fireStation.getId());
    fireStationEntity.setAddress(fireStation.getAddress());
    return fireStationEntity;
  }
}
