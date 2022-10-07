package com.safetynet.safetynetalerts.dao.json.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * FireStationJson is JSON model
 * 
 * @author MC
 * @version 1.0
 */
public class FireStationJson {
  // { "address":"1509 Culver St", "station":"3" }

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  private String station;

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, station);
  }

  /**
   * Compare two objects
   * 
   * @param obj Object to compare
   * @return True if the objects are equal, and false if not.
   */
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
