package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * MedicalRecord is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
@Getter
@Setter
public class MedicalRecord {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("birthdate")
  private String birthdate;

  @JsonProperty("medications")
  @Valid
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  private List<Allergy> allergies = new ArrayList<>();

  /**
   * Add medication to medication list
   * 
   * @param medicationsItem Medication to add
   * @return Medications list
   */
  public MedicalRecord addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  /**
   * Add allergy to allergy list
   * 
   * @param allergiesItem Object to add
   * @return Allergies list
   */
  public MedicalRecord addAllergiesItem(Allergy allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

  /**
   * Compare two objects
   * 
   * @param o Object to compare
   * @return True if the objects are equal, and false if not.
   */
  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    var medicalRecord = (MedicalRecord) o;
    return
    // Objects.equals(this.id, medicalRecord.id) &&
    Objects.equals(this.firstName, medicalRecord.firstName)
        && Objects.equals(this.lastName, medicalRecord.lastName)
        && Objects.equals(this.birthdate, medicalRecord.birthdate)
        && Objects.equals(this.medications, medicalRecord.medications)
        && Objects.equals(this.allergies, medicalRecord.allergies);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, birthdate, medications, allergies);
  }
}
