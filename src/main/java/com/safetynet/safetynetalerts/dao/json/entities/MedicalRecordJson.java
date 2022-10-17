package com.safetynet.safetynetalerts.dao.json.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * MedicalRecordJson is JSON model
 * 
 * @author MC
 * @version 1.0
 */
@Getter
@Setter
public class MedicalRecordJson {
  // { "firstName":"John", "lastName":"Boyd", "birthdate":"03/06/1984", "medications":["aznol:350mg", "hydrapermazol:100mg"], "allergies":["nillacilan"] }

  private String firstName;
  private String lastName;
  private String birthdate;
  private List<String> medications = new ArrayList<>();
  private List<String> allergies = new ArrayList<>();

  /**
   * Add allergy to allergy list
   * 
   * @param allergiesItem Allergy to add
   * @return Allergy list
   */
  public MedicalRecordJson addAllergiesItem(String allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

  /**
   * Add medication to medication list
   * 
   * @param medicationsItem Medication to add
   * @return Medication list
   */
  public MedicalRecordJson addMedicationsItem(String medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }
}
