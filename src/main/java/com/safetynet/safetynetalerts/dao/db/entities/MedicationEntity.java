package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class MedicationEntity {
  @Getter @Setter
  private Integer id;
  
  @Getter @Setter
  private String medication;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(id, medication);
  }

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
