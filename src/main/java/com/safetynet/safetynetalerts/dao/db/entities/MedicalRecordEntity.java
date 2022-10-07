package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * MedicalRecordEntity is Entity model
 * 
 * @author MC
 * @version 1.0
 */
public class MedicalRecordEntity {
  @Getter
  @Setter
  private Integer idPerson;

  @Getter
  @Setter
  private List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();

  @Getter
  @Setter
  private List<MedicalRecordMedicationEntity> medications = new ArrayList<>();

  /**
   * Add allergy to allergy list
   * 
   * @param medicalRecordAllergiesItem Object to add
   * @return Allergies list
   */
  public MedicalRecordEntity addMedicalRecordAllergiesItem(MedicalRecordAllergyEntity medicalRecordAllergiesItem) {
    this.allergies.add(medicalRecordAllergiesItem);
    return this;
  }

  /**
   * Add medication to medication list
   * 
   * @param medicalRecordMedicationsItem Object to add
   * @return Medications list
   */
  public MedicalRecordEntity addMedicalRecordMedicationsItem(MedicalRecordMedicationEntity medicalRecordMedicationsItem) {
    this.medications.add(medicalRecordMedicationsItem);
    return this;
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(allergies, idPerson, medications);
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
    var other = (MedicalRecordEntity) obj;
    return Objects.equals(allergies, other.allergies)
        && Objects.equals(idPerson, other.idPerson)
        && Objects.equals(medications, other.medications);
  }
}
