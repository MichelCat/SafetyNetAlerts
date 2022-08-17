package io.swagger.dao.db.entities;

public class MedicalRecordAllergyEntity implements Comparable<MedicalRecordAllergyEntity> {
  private Integer idAlergy;

  public Integer getIdAlergy() {
    return idAlergy;
  }

  public void setIdAlergy(Integer idAlergy) {
    this.idAlergy = idAlergy;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordAllergyEntity o) {
    return this.idAlergy.compareTo(o.idAlergy);
  }
}
