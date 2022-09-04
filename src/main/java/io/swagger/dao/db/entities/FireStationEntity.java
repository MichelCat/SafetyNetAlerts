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
    if (station.equals(o.station)) {
      return address.compareToIgnoreCase(o.address);
    } else {
      return station.compareTo(o.station);
    }
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "FireStationEntity [station=" + station + ", address=" + address + "]";
  }
}
