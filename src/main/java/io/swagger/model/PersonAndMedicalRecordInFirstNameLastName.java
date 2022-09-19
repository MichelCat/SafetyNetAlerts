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
 * PersonAndMedicalRecordInFirstNameLastName
 */
@Validated
public class PersonAndMedicalRecordInFirstNameLastName   {
  @JsonProperty("firstName")
  @Getter @Setter
  private String firstName = null;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName = null;

  @JsonProperty("address")
  @Getter @Setter
  private String address = null;

  @JsonProperty("age")
  @Getter @Setter
  private Integer age = null;

  @JsonProperty("email")
  @Getter @Setter
  private String email = null;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = null;

  public PersonAndMedicalRecordInFirstNameLastName firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName address(String address) {
    this.address = address;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName age(Integer age) {
    this.age = age;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName email(String email) {
    this.email = email;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName medications(List<Medication> medications) {
    this.medications = medications;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName addMedicationsItem(Medication medicationsItem) {
    if (this.medications == null) {
      this.medications = new ArrayList<Medication>();
    }
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName allergies(List<Allergy> allergies) {
    this.allergies = allergies;
    return this;
  }

  public PersonAndMedicalRecordInFirstNameLastName addAllergiesItem(Allergy allergiesItem) {
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
    PersonAndMedicalRecordInFirstNameLastName personAndMedicalRecordInFirstNameLastName = (PersonAndMedicalRecordInFirstNameLastName) o;
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

  @Override
  public String toString() {
    return "PersonAndMedicalRecordInFirstNameLastName [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", age=" + age + ", email=" + email + ", medications="
        + medications + ", allergies=" + allergies + "]";
  }
}
