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
 * PersonAndMedicalRecordInFireStation
 */
@Validated
public class PersonAndMedicalRecordInFireStation   {
  @JsonProperty("address")
  @Getter @Setter
  private String address;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = new ArrayList<>();

  @JsonProperty("phoneNumber")
  @Getter @Setter
  private String phoneNumber;

  @JsonProperty("age")
  @Getter @Setter
  private Integer age;

  @JsonProperty("fireStations")
  @Valid
  @Getter @Setter
  private List<FireStation> fireStations = new ArrayList<>();

  public PersonAndMedicalRecordInFireStation addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInFireStation addAllergiesItem(Allergy allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

  public PersonAndMedicalRecordInFireStation addFireStationsItem(FireStation fireStationsItem) {
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
    var personAndMedicalRecordInFireStation = (PersonAndMedicalRecordInFireStation) o;
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
}
