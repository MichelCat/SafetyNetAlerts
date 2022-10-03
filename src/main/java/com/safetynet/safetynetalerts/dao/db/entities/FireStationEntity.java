package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class FireStationEntity {
  @Getter @Setter
  private Integer station;
  
  @Getter @Setter
  private String address;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(address, station);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    var other = (FireStationEntity) obj;
    return address.equalsIgnoreCase(other.address)
        && Objects.equals(station, other.station);
  }
}
