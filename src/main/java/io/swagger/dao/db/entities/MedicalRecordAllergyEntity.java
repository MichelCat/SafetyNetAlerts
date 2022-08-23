package io.swagger.dao.db.entities;

import lombok.Getter;
import lombok.Setter;

public class MedicalRecordAllergyEntity implements Comparable<MedicalRecordAllergyEntity> {
  @Getter @Setter
  private Integer idAlergy;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordAllergyEntity o) {
    return this.idAlergy.compareTo(o.idAlergy);
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "MedicalRecordAllergyEntity [idAlergy=" + idAlergy + "]";
  }
}
