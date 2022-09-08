package io.swagger.data;

import io.swagger.model.FireStation;
import io.swagger.dao.db.entities.FireStationEntity;

public class FireStationData {

  public static FireStation getFireStationWallStreet() {
    FireStation fireStation = new FireStation();
    fireStation.setId(3);
    fireStation.setAddress("1234 Wall Street");
    return fireStation;
  }

  public static FireStationEntity getFireStationEntityWallStreet() {
    FireStationEntity fireStationEntity = new FireStationEntity();
    fireStationEntity.setStation(3);
    fireStationEntity.setAddress("1234 Wall Street");
    return fireStationEntity;
  }

  public static String getJsonWallStreet() {
    String jsonString= "{"
        + "\"id\": \"3\" ,"
        + "\"address\": \"1234 Wall Street\""
        + "}";
    return jsonString;
  }
}
