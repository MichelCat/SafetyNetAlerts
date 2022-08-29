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
 * PersonAndMedicalRecordInFirstNameLastName
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-29T17:07:42.572Z[GMT]")


public class PersonAndMedicalRecordInFirstNameLastName   {
  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("address")
  private String address = null;

  @JsonProperty("age")
  private Integer age = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("medications")
  @Valid
  private List<Medication> medications = null;

  @JsonProperty("allergies")
  @Valid
  private List<Allergy> allergies = null;

  public PersonAndMedicalRecordInFirstNameLastName firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  @Schema(description = "")
  
    public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public PersonAndMedicalRecordInFirstNameLastName lastName(String lastName) {
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

  public PersonAndMedicalRecordInFirstNameLastName address(String address) {
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

  public PersonAndMedicalRecordInFirstNameLastName age(Integer age) {
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

  public PersonAndMedicalRecordInFirstNameLastName email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @Schema(description = "")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonAndMedicalRecordInFirstNameLastName {\n");
    
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    medications: ").append(toIndentedString(medications)).append("\n");
    sb.append("    allergies: ").append(toIndentedString(allergies)).append("\n");
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
