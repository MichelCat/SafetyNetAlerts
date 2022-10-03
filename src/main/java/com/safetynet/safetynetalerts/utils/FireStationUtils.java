package com.safetynet.safetynetalerts.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.model.FireStation;

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
