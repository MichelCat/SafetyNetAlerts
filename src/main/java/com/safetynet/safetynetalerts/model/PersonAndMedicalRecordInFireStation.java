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
 * PersonAndMedicalRecordInFireStation is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
@Getter
@Setter
public class PersonAndMedicalRecordInFireStation {
  @JsonProperty("address")
  private String address;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("medications")
  @Valid
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  private List<Allergy> allergies = new ArrayList<>();

  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("fireStations")
  @Valid
  private List<FireStation> fireStations = new ArrayList<>();

  /**
   * Add medication to medication list
   * 
   * @param medicationsItem Medication to add
   * @return Medication list
   */
  public PersonAndMedicalRecordInFireStation addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  /**
   * Add allergy to allergy list
   * 
   * @param allergiesItem Object to add
   * @return Allergies list
   */
  public PersonAndMedicalRecordInFireStation addAllergiesItem(Allergy allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

  /**
   * Add fire station to fire station list
   * 
   * @param fireStationsItem Fire station to add
   * @return Fire station list
   */
  public PersonAndMedicalRecordInFireStation addFireStationsItem(FireStation fireStationsItem) {
    this.fireStations.add(fireStationsItem);
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
    var personAndMedicalRecordInFireStation = (PersonAndMedicalRecordInFireStation) o;
    return Objects.equals(this.address, personAndMedicalRecordInFireStation.address)
        && Objects.equals(this.lastName, personAndMedicalRecordInFireStation.lastName)
        && Objects.equals(this.medications, personAndMedicalRecordInFireStation.medications)
        && Objects.equals(this.allergies, personAndMedicalRecordInFireStation.allergies)
        && Objects.equals(this.phoneNumber, personAndMedicalRecordInFireStation.phoneNumber)
        && Objects.equals(this.age, personAndMedicalRecordInFireStation.age)
        && Objects.equals(this.fireStations, personAndMedicalRecordInFireStation.fireStations);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, lastName, medications, allergies, phoneNumber, age, fireStations);
  }
}
