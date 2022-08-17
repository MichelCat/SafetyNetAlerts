package io.swagger.dao.db.entities;

public class MedicalRecordMedicationEntity implements Comparable<MedicalRecordMedicationEntity> {
  private Integer idMedication;
  private String dosage;

  public Integer getIdMedication() {
    return idMedication;
  }

  public void setIdMedication(Integer idMedication) {
    this.idMedication = idMedication;
  }

  public String getDosage() {
    return dosage;
  }

  public void setDosage(String dosage) {
    this.dosage = dosage;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordMedicationEntity o) {
    return this.idMedication.compareTo(o.idMedication);
  }
}
