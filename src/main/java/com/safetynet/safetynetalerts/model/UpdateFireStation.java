package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * UpdateFireStation is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public class UpdateFireStation {
  @JsonProperty("oldStation")
  @Getter
  @Setter
  private Integer oldStation;

  @JsonProperty("newStation")
  @Getter
  @Setter
  private Integer newStation;

  @JsonProperty("address")
  @Getter
  @Setter
  private String address;

  /**
   * Compare two objects
   * 
   * @param o Object to compare
   * @return True if the objects are equal, and false if not.
   */
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    var updateFireStation = (UpdateFireStation) o;
    return Objects.equals(this.oldStation, updateFireStation.oldStation)
        && Objects.equals(this.newStation, updateFireStation.newStation)
        && Objects.equals(this.address, updateFireStation.address);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(oldStation, newStation, address);
  }
}
