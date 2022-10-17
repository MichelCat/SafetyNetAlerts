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
 * PersonAndMedicalRecordInFirstNameLastName is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
@Getter
@Setter
public class PersonAndMedicalRecordInFirstNameLastName {
  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("address")
  private String address;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("email")
  private String email;

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
   * @return Medication list
   */
  public PersonAndMedicalRecordInFirstNameLastName addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  /**
   * Add allergy to allergy list
   * 
   * @param allergiesItem Allergy to add
   * @return Allergies list
   */
  public PersonAndMedicalRecordInFirstNameLastName addAllergiesItem(Allergy allergiesItem) {
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
    var personAndMedicalRecordInFirstNameLastName = (PersonAndMedicalRecordInFirstNameLastName) o;
    return Objects.equals(this.firstName, personAndMedicalRecordInFirstNameLastName.firstName)
        && Objects.equals(this.lastName, personAndMedicalRecordInFirstNameLastName.lastName)
        && Objects.equals(this.address, personAndMedicalRecordInFirstNameLastName.address)
        && Objects.equals(this.age, personAndMedicalRecordInFirstNameLastName.age)
        && Objects.equals(this.email, personAndMedicalRecordInFirstNameLastName.email)
        && Objects.equals(this.medications, personAndMedicalRecordInFirstNameLastName.medications)
        && Objects.equals(this.allergies, personAndMedicalRecordInFirstNameLastName.allergies);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, address, age, email, medications, allergies);
  }
}
