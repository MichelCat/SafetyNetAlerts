package com.safetynet.safetynetalerts.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.model.FireStation;

/**
 * FireStationUtils is an FireStation object conversion utility class
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class FireStationUtils {

  /**
   * Conversion FireStationEntity list to FireStation list
   * 
   * @param fireStationEntities FireStationEntity list
   * @return FireStation list
   */
  public List<FireStation> conversionListFireStationEntityToFireStation(List<FireStationEntity> fireStationEntities) {
    List<FireStation> fireStations = new ArrayList<>();
    fireStationEntities.forEach(e -> {
      fireStations.add(conversionFireStationEntityToFireStation(e));
    });
    return fireStations;
  }

  /**
   * Conversion FireStationEntity to FireStation
   * 
   * @param fireStationEntity FireStationEntity object
   * @return FireStation
   */
  public FireStation conversionFireStationEntityToFireStation(FireStationEntity fireStationEntity) {
    var fireStation = new FireStation();
    fireStation.setId(fireStationEntity.getStation());
    fireStation.setAddress(fireStationEntity.getAddress());
    return fireStation;
  }

  /**
   * Conversion FireStation to FireStationEntity
   * 
   * @param fireStation FireStation object
   * @return FireStationEntity
   */
  public FireStationEntity conversionFireStationToFireStationEntity(FireStation fireStation) {
    var fireStationEntity = new FireStationEntity();
    fireStationEntity.setStation(fireStation.getId());
    fireStationEntity.setAddress(fireStation.getAddress());
    return fireStationEntity;
  }
}
