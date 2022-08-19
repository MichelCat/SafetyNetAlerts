package io.swagger.dao.db.entities;

import lombok.Getter;
import lombok.Setter;

public class FireStationEntity implements Comparable<FireStationEntity> {
  @Getter @Setter
  private Integer station;
  
  @Getter @Setter
  private String address;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(FireStationEntity o) {
    return this.station.compareTo(o.station);
  }
}
