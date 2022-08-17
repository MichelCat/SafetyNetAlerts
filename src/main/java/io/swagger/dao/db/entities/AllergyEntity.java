package io.swagger.dao.db.entities;

public class AllergyEntity implements Comparable<AllergyEntity> {
  private Integer id;
  private String allergy;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAllergy() {
    return allergy;
  }

  public void setAllergy(String allergy) {
    this.allergy = allergy;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(AllergyEntity o) {
    return this.allergy.compareTo(o.allergy);
  }
}
