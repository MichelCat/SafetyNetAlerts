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
 * MedicalRecord
 */
@Validated
public class MedicalRecord   {
  @JsonProperty("id")
  @Getter @Setter
  private Integer id;

  @JsonProperty("firstName")
  @Getter @Setter
  private String firstName;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName;

  @JsonProperty("birthdate")
  @Getter @Setter
  private String birthdate;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = new ArrayList<>();

  public MedicalRecord addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  public MedicalRecord addAllergiesItem(Allergy allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

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
//        Objects.equals(this.id, medicalRecord.id) &&
        Objects.equals(this.firstName, medicalRecord.firstName) &&
        Objects.equals(this.lastName, medicalRecord.lastName) &&
        Objects.equals(this.birthdate, medicalRecord.birthdate) &&
        Objects.equals(this.medications, medicalRecord.medications) &&
        Objects.equals(this.allergies, medicalRecord.allergies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, birthdate, medications, allergies);
  }
}