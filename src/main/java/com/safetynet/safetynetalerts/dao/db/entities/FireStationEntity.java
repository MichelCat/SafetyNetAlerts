package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * FireStationEntity is Entity model
 * 
 * @author MC
 * @version 1.0
 */
public class FireStationEntity {
  @Getter
  @Setter
  private Integer station;

  @Getter
  @Setter
  private String address;

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
    var other = (FireStationEntity) obj;
    return address.equalsIgnoreCase(other.address)
        && Objects.equals(station, other.station);
  }
}
