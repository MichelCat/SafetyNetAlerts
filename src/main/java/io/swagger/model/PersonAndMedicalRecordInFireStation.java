package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PersonAndMedicalRecordInFireStation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-27T21:21:17.443Z[GMT]")


public class PersonAndMedicalRecordInFireStation   {
  @JsonProperty("address")
  private String address = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("medications")
  @Valid
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  private List<Allergy> allergies = null;

  @JsonProperty("phoneNumber")
  private String phoneNumber = null;

  @JsonProperty("age")
  private Integer age = null;

  @JsonProperty("stationNumber")
  private Integer stationNumber = null;

  public PersonAndMedicalRecordInFireStation address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
   **/
  @Schema(description = "")
  
    public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public PersonAndMedicalRecordInFireStation lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  @Schema(description = "")
  
    public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public PersonAndMedicalRecordInFireStation phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
   **/
  @Schema(description = "")
  
    public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public PersonAndMedicalRecordInFireStation age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Get age
   * @return age
   **/
  @Schema(description = "")
  
    public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public PersonAndMedicalRecordInFireStation stationNumber(Integer stationNumber) {
    this.stationNumber = stationNumber;
    return this;
  }

  /**
   * Get stationNumber
   * @return stationNumber
   **/
  @Schema(description = "")
  
    public Integer getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(Integer stationNumber) {
    this.stationNumber = stationNumber;
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
        Objects.equals(this.stationNumber, personAndMedicalRecordInFireStation.stationNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, lastName, medications, allergies, phoneNumber, age, stationNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonAndMedicalRecordInFireStation {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    medications: ").append(toIndentedString(medications)).append("\n");
    sb.append("    allergies: ").append(toIndentedString(allergies)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    stationNumber: ").append(toIndentedString(stationNumber)).append("\n");
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
