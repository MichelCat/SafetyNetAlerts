package com.safetynet.safetynetalerts.dao.db.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class MedicalRecordEntity {
  @Getter @Setter
  private Integer idPerson;
  
  @Getter @Setter
  private List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();
  
  @Getter @Setter
  private List<MedicalRecordMedicationEntity> medications = new ArrayList<>();
  
  public MedicalRecordEntity addMedicalRecordAllergiesItem(MedicalRecordAllergyEntity medicalRecordAllergiesItem) {
    this.allergies.add(medicalRecordAllergiesItem);
    return this;
  }
  
  public MedicalRecordEntity addMedicalRecordMedicationsItem(MedicalRecordMedicationEntity medicalRecordMedicationsItem) {
    this.medications.add(medicalRecordMedicationsItem);
    return this;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(allergies, idPerson, medications);
  }

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
