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
    if (getStation().equals(o.getStation())) {
      return getAddress().compareTo(o.getAddress());
    } else {
      return getStation().compareTo(o.getStation());
    }
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "FireStationEntity [station=" + station + ", address=" + address + "]";
  }
}
