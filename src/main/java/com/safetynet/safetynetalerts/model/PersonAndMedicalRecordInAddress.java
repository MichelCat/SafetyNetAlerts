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
 * PersonAndMedicalRecordInAddress
 */
@Validated
public class PersonAndMedicalRecordInAddress   {
  @JsonProperty("person")
  @Valid
  @Getter @Setter
  private Person person;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = new ArrayList<>();

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = new ArrayList<>();

  @JsonProperty("fireStations")
  @Valid
  @Getter @Setter
  private List<FireStation> fireStations = new ArrayList<>();

  public PersonAndMedicalRecordInAddress addMedicationsItem(Medication medicationsItem) {
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInAddress addAllergiesItem(Allergy allergiesItem) {
    this.allergies.add(allergiesItem);
    return this;
  }

  public PersonAndMedicalRecordInAddress addFireStationsItem(FireStation fireStationsItem) {
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
    var personAndMedicalRecordInAddress = (PersonAndMedicalRecordInAddress) o;
    return Objects.equals(this.person, personAndMedicalRecordInAddress.person) &&
        Objects.equals(this.medications, personAndMedicalRecordInAddress.medications) &&
        Objects.equals(this.allergies, personAndMedicalRecordInAddress.allergies) &&
        Objects.equals(this.fireStations, personAndMedicalRecordInAddress.fireStations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, medications, allergies, fireStations);
  }
}
