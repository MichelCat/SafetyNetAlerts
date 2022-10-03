package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class AllergyEntity {
  @Getter @Setter
  private Integer id;
  
  @Getter @Setter
  private String allergy;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(allergy, id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    var other = (AllergyEntity) obj;
    return allergy.equalsIgnoreCase(other.allergy)
        && Objects.equals(id, other.id);
  }
}
