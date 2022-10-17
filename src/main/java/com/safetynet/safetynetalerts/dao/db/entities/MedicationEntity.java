package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * MedicationEntity is Entity model
 * 
 * @author MC
 * @version 1.0
 */
@Getter
@Setter
public class MedicationEntity {
  private Integer id;
  private String medication;

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, medication);
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
    var other = (MedicationEntity) obj;
    return Objects.equals(id, other.id)
        && medication.equalsIgnoreCase(other.medication);
  }
}
