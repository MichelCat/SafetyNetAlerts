package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Allergy is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public class Allergy {
  @JsonProperty("allergy")
  @Getter
  @Setter
  private String allergy;

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
    var allergy = (Allergy) o;
    return Objects.equals(this.allergy, allergy.allergy);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(allergy);
  }
}
