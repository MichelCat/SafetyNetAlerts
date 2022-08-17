package io.swagger.dao.db.entities;

public class MedicationEntity implements Comparable<MedicationEntity> {
  private Integer id;
  private String medication;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMedication() {
    return medication;
  }

  public void setMedication(String medication) {
    this.medication = medication;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicationEntity o) {
    return this.medication.compareTo(o.medication);
  }
}
