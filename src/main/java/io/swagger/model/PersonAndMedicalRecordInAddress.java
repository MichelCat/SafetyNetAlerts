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
 * PersonAndMedicalRecordInAddress
 */
@Validated
public class PersonAndMedicalRecordInAddress   {
  @JsonProperty("person")
  @Valid
  @Getter @Setter
  private Person person = null;

  @JsonProperty("medications")
  @Valid
  @Getter @Setter
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  @Getter @Setter
  private List<Allergy> allergies = null;

  @JsonProperty("fireStations")
  @Valid
  @Getter @Setter
  private List<FireStation> fireStations = null;

  public PersonAndMedicalRecordInAddress person(Person person) {
    this.person = person;
    return this;
  }

  public PersonAndMedicalRecordInAddress medications(List<Medication> medications) {
    this.medications = medications;
    return this;
  }

  public PersonAndMedicalRecordInAddress addMedicationsItem(Medication medicationsItem) {
    if (this.medications == null) {
      this.medications = new ArrayList<Medication>();
    }
    this.medications.add(medicationsItem);
    return this;
  }

  public PersonAndMedicalRecordInAddress allergies(List<Allergy> allergies) {
    this.allergies = allergies;
    return this;
  }

  public PersonAndMedicalRecordInAddress addAllergiesItem(Allergy allergiesItem) {
    if (this.allergies == null) {
      this.allergies = new ArrayList<Allergy>();
    }
    this.allergies.add(allergiesItem);
    return this;
  }

  public PersonAndMedicalRecordInAddress fireStations(List<FireStation> fireStations) {
    this.fireStations = fireStations;
    return this;
  }

  public PersonAndMedicalRecordInAddress addFireStationsItem(FireStation fireStationsItem) {
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
    PersonAndMedicalRecordInAddress personAndMedicalRecordInAddress = (PersonAndMedicalRecordInAddress) o;
    return Objects.equals(this.person, personAndMedicalRecordInAddress.person) &&
        Objects.equals(this.medications, personAndMedicalRecordInAddress.medications) &&
        Objects.equals(this.allergies, personAndMedicalRecordInAddress.allergies) &&
        Objects.equals(this.fireStations, personAndMedicalRecordInAddress.fireStations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, medications, allergies, fireStations);
  }

  @Override
  public String toString() {
    return "PersonAndMedicalRecordInAddress [person=" + person + ", medications=" + medications + ", allergies=" + allergies + ", fireStations=" + fireStations + "]";
  }
}
