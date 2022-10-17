package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Person is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
@Getter
@Setter
public class Person {
  @JsonProperty("id")
  private Integer id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("address")
  private String address;

  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("zipCode")
  private String zipCode;

  @JsonProperty("age")
  private Integer age;

  @JsonProperty("city")
  private String city;

  @JsonProperty("birthdate")
  private String birthdate;

  @JsonProperty("email")
  private String email;

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
    var person = (Person) o;
    return
    // Objects.equals(this.id, person.id) &&
    Objects.equals(this.firstName, person.firstName)
      && Objects.equals(this.lastName, person.lastName)
      && Objects.equals(this.address, person.address)
      && Objects.equals(this.phoneNumber, person.phoneNumber)
      && Objects.equals(this.zipCode, person.zipCode)
      && Objects.equals(this.age, person.age)
      && Objects.equals(this.city, person.city)
      && Objects.equals(this.birthdate, person.birthdate)
      && Objects.equals(this.email, person.email);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, phoneNumber, zipCode, age, city, birthdate, email);
  }
}
