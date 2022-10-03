package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class MedicalRecordMedicationEntity {
  @Getter @Setter
  private Integer idMedication;
  
  @Getter @Setter
  private String dosage;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(dosage, idMedication);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    var other = (MedicalRecordMedicationEntity) obj;
    return Objects.equals(dosage, other.dosage)
        && Objects.equals(idMedication, other.idMedication);
  }
}
