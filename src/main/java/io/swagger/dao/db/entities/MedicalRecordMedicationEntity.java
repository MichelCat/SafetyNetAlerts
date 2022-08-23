package io.swagger.dao.db.entities;

import lombok.Getter;
import lombok.Setter;

public class MedicalRecordMedicationEntity implements Comparable<MedicalRecordMedicationEntity> {
  @Getter @Setter
  private Integer idMedication;
  
  @Getter @Setter
  private String dosage;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordMedicationEntity o) {
    return this.idMedication.compareTo(o.idMedication);
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "MedicalRecordMedicationEntity [idMedication=" + idMedication + ", dosage=" + dosage + "]";
  }
}
