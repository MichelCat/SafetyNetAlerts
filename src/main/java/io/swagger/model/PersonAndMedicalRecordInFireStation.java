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
 * PersonAndMedicalRecordInFireStation
 */
@Validated
public class PersonAndMedicalRecordInFireStation   {
  @JsonProperty("address")
  @Getter @Setter
  private String address = null;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName = null;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = null;

  @JsonProperty("phoneNumber")
  @Getter @Setter
  private String phoneNumber = null;

  @JsonProperty("age")
  @Getter @Setter
  private Integer age = null;

  @JsonProperty("fireStations")
  @Valid
  @Getter @Setter
  private List<FireStation> fireStations = null;

  public PersonAndMedicalRecordInFireStation address(String address) {
    this.address = address;
    return this;
  }

  public PersonAndMedicalRecordInFireStation lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public PersonAndMedicalRecordInFireStation medications(List<Medication> medications) {
    this.medications = medications;
    return this;
  }

  public PersonAndMedicalRecordInFireStation addMedicationsItem(Medication medicationsItem) {
    if (this.medications == null) {
      this.medications = new ArrayList<Medication>();
    }
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInFireStation allergies(List<Allergy> allergies) {
    this.allergies = allergies;
    return this;
  }

  public PersonAndMedicalRecordInFireStation addAllergiesItem(Allergy allergiesItem) {
    if (this.allergies == null) {
      this.allergies = new ArrayList<Allergy>();
    }
    this.allergies.add(allergiesItem);
    return this;
  }

  public PersonAndMedicalRecordInFireStation phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public PersonAndMedicalRecordInFireStation age(Integer age) {
    this.age = age;
    return this;
  }

  public PersonAndMedicalRecordInFireStation fireStations(List<FireStation> fireStations) {
    this.fireStations = fireStations;
    return this;
  }

  public PersonAndMedicalRecordInFireStation addFireStationsItem(FireStation fireStationsItem) {
    if (this.fireStations == null) {
      this.fireStations = new ArrayList<FireStation>();
    }
    this.fireStations.add(fireStationsItem);
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
    PersonAndMedicalRecordInFireStation personAndMedicalRecordInFireStation = (PersonAndMedicalRecordInFireStation) o;
    return Objects.equals(this.address, personAndMedicalRecordInFireStation.address) &&
        Objects.equals(this.lastName, personAndMedicalRecordInFireStation.lastName) &&
        Objects.equals(this.medications, personAndMedicalRecordInFireStation.medications) &&
        Objects.equals(this.allergies, personAndMedicalRecordInFireStation.allergies) &&
        Objects.equals(this.phoneNumber, personAndMedicalRecordInFireStation.phoneNumber) &&
        Objects.equals(this.age, personAndMedicalRecordInFireStation.age) &&
        Objects.equals(this.fireStations, personAndMedicalRecordInFireStation.fireStations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, lastName, medications, allergies, phoneNumber, age, fireStations);
  }

  @Override
  public String toString() {
    return "PersonAndMedicalRecordInFireStation [address=" + address + ", lastName=" + lastName + ", medications=" + medications + ", allergies=" + allergies + ", phoneNumber=" + phoneNumber
        + ", age=" + age + ", fireStations=" + fireStations + "]";
  }
}
