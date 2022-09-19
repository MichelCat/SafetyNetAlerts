package io.swagger.model;

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
  private Integer id = null;

  @JsonProperty("firstName")
  @Getter @Setter
  private String firstName = null;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName = null;

  @JsonProperty("birthdate")
  @Getter @Setter
  private String birthdate = null;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = null;

  public MedicalRecord id(Integer id) {
    this.id = id;
    return this;
  }

  public MedicalRecord firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public MedicalRecord lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public MedicalRecord birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  public MedicalRecord medications(List<Medication> medications) {
    this.medications = medications;
    return this;
  }

  public MedicalRecord addMedicationsItem(Medication medicationsItem) {
    if (this.medications == null) {
      this.medications = new ArrayList<Medication>();
    }
    this.medications.add(medicationsItem);
    return this;
  }

  public MedicalRecord allergies(List<Allergy> allergies) {
    this.allergies = allergies;
    return this;
  }

  public MedicalRecord addAllergiesItem(Allergy allergiesItem) {
    if (this.allergies == null) {
      this.allergies = new ArrayList<Allergy>();
    }
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
    MedicalRecord medicalRecord = (MedicalRecord) o;
    return Objects.equals(this.id, medicalRecord.id) &&
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

  @Override
  public String toString() {
    return "MedicalRecord [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate + ", medications=" + medications + ", allergies=" + allergies + "]";
  }
}
