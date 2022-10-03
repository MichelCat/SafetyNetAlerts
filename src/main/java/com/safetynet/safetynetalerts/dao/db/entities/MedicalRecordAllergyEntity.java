package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class MedicalRecordAllergyEntity {
  @Getter @Setter
  private Integer idAlergy;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(idAlergy);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    var other = (MedicalRecordAllergyEntity) obj;
    return Objects.equals(idAlergy, other.idAlergy);
  }
}
