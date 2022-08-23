package io.swagger.dao.db.entities;

import lombok.Getter;
import lombok.Setter;

public class MedicationEntity implements Comparable<MedicationEntity> {
  @Getter @Setter
  private Integer id;
  
  @Getter @Setter
  private String medication;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicationEntity o) {
    return this.medication.compareTo(o.medication);
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "MedicationEntity [id=" + id + ", medication=" + medication + "]";
  }
}
