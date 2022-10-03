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
 * PersonAndMedicalRecordInFirstNameLastName
 */
@Validated
public class PersonAndMedicalRecordInFirstNameLastName   {
  @JsonProperty("firstName")
  @Getter @Setter
  private String firstName;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName;

  @JsonProperty("address")
  @Getter @Setter
  private String address;

  @JsonProperty("age")
  @Getter @Setter
  private Integer age;

  @JsonProperty("email")
  @Getter @Setter
  private String email;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = new ArrayList<>();

  public PersonAndMedicalRecordInFirstNameLastName addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName addAllergiesItem(Allergy allergiesItem) {
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
    var personAndMedicalRecordInFirstNameLastName = (PersonAndMedicalRecordInFirstNameLastName) o;
    return Objects.equals(this.firstName, personAndMedicalRecordInFirstNameLastName.firstName) &&
        Objects.equals(this.lastName, personAndMedicalRecordInFirstNameLastName.lastName) &&
        Objects.equals(this.address, personAndMedicalRecordInFirstNameLastName.address) &&
        Objects.equals(this.age, personAndMedicalRecordInFirstNameLastName.age) &&
        Objects.equals(this.email, personAndMedicalRecordInFirstNameLastName.email) &&
        Objects.equals(this.medications, personAndMedicalRecordInFirstNameLastName.medications) &&
        Objects.equals(this.allergies, personAndMedicalRecordInFirstNameLastName.allergies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, address, age, email, medications, allergies);
  }
}
