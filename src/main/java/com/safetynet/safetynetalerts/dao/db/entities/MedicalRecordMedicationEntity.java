package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * MedicalRecordMedicationEntity is Entity model
 * 
 * @author MC
 * @version 1.0
 */
@Getter
@Setter
public class MedicalRecordMedicationEntity {
  private Integer idMedication;
  private String dosage;

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(dosage, idMedication);
  }

  /**
   * Compare two objects
   * 
   * @param obj Object to compare
   * @return True if the objects are equal, and false if not.
   */
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
