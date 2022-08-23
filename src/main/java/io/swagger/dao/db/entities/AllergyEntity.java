package io.swagger.dao.db.entities;

import lombok.Getter;
import lombok.Setter;

public class AllergyEntity implements Comparable<AllergyEntity> {
  @Getter @Setter
  private Integer id;
  
  @Getter @Setter
  private String allergy;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(AllergyEntity o) {
    return this.allergy.compareTo(o.allergy);
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "AllergyEntity [id=" + id + ", allergy=" + allergy + "]";
  }
}
