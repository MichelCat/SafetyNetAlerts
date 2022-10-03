package com.safetynet.safetynetalerts.data;

import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.UpdateFireStation;

public class FireStationData {

  public static FireStation getFireStationWallStreet() {
    FireStation fireStation = new FireStation();
    fireStation.setId(1);
    fireStation.setAddress("1234 Wall Street");
    return fireStation;
  }

  public static FireStationEntity getFireStationEntityWallStreet() {
    FireStationEntity fireStationEntity = new FireStationEntity();
    fireStationEntity.setStation(1);
    fireStationEntity.setAddress("1234 Wall Street");
    return fireStationEntity;
  }

  public static String getJsonWallStreet() {
    String jsonString= "{"
        + "\"id\": \"1\" ,"
        + "\"address\": \"1234 Wall Street\""
        + "}";
    return jsonString;
  }
  
  public static String getJsonUpdateWallStreet() {
    String jsonString= "{"
        + "\"oldStation\": \"1\" ,"
        + "\"newStation\": \"9\" ,"
        + "\"address\": \"1234 Wall Street\""
        + "}";
    return jsonString;
  }

  public static UpdateFireStation getUpdateFireStationWallStreet() {
    UpdateFireStation updateFireStation = new UpdateFireStation();
    updateFireStation.setOldStation(1);
    updateFireStation.setNewStation(9);
    updateFireStation.setAddress("1234 Wall Street");
    return updateFireStation;
  }
}
