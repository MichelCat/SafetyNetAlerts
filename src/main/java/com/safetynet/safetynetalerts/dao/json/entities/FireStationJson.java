package com.safetynet.safetynetalerts.dao.json.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

// { "address":"1509 Culver St", "station":"3" }
public class FireStationJson {

  @Getter @Setter
  private String address;
  
  @Getter @Setter
  private String station;

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
    FireStationJson other = (FireStationJson) obj;
    return Objects.equals(address, other.address) && Objects.equals(station, other.station);
  }
}
