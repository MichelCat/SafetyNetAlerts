package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PersonAndMedicalRecordInAddress
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-22T11:20:27.360Z[GMT]")


public class PersonAndMedicalRecordInAddress   {
  @JsonProperty("person")
  private Person person = null;

  @JsonProperty("medications")
  @Valid
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  private List<Allergy> allergies = null;

  @JsonProperty("fireStation")
  private FireStation fireStation = null;

  public PersonAndMedicalRecordInAddress person(Person person) {
    this.person = person;
    return this;
  }

  /**
   * Get person
   * @return person
   **/
  @Schema(description = "")
  
    @Valid
    public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
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

  /**
   * Get medications
   * @return medications
   **/
  @Schema(description = "")
      @Valid
    public List<Medication> getMedications() {
    return medications;
  }

  public void setMedications(List<Medication> medications) {
    this.medications = medications;
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

  /**
   * Get allergies
   * @return allergies
   **/
  @Schema(description = "")
      @Valid
    public List<Allergy> getAllergies() {
    return allergies;
  }

  public void setAllergies(List<Allergy> allergies) {
    this.allergies = allergies;
  }

  public PersonAndMedicalRecordInAddress fireStation(FireStation fireStation) {
    this.fireStation = fireStation;
    return this;
  }

  /**
   * Get fireStation
   * @return fireStation
   **/
  @Schema(description = "")
  
    @Valid
    public FireStation getFireStation() {
    return fireStation;
  }

  public void setFireStation(FireStation fireStation) {
    this.fireStation = fireStation;
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
        Objects.equals(this.fireStation, personAndMedicalRecordInAddress.fireStation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person, medications, allergies, fireStation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonAndMedicalRecordInAddress {\n");
    
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
    sb.append("    medications: ").append(toIndentedString(medications)).append("\n");
    sb.append("    allergies: ").append(toIndentedString(allergies)).append("\n");
    sb.append("    fireStation: ").append(toIndentedString(fireStation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
