package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Person
 */
@Validated
public class Person   {
  @JsonProperty("id")
  @Getter @Setter
  private Integer id = null;

  @JsonProperty("firstName")
  @Getter @Setter
  private String firstName = null;

  @JsonProperty("lastName")
  @Getter @Setter
  private String lastName = null;

  @JsonProperty("address")
  @Getter @Setter
  private String address = null;

  @JsonProperty("phoneNumber")
  @Getter @Setter
  private String phoneNumber = null;

  @JsonProperty("zipCode")
  @Getter @Setter
  private String zipCode = null;

  @JsonProperty("age")
  @Getter @Setter
  private Integer age = null;

  @JsonProperty("city")
  @Getter @Setter
  private String city = null;

  @JsonProperty("birthdate")
  @Getter @Setter
  private String birthdate = null;

  @JsonProperty("email")
  @Getter @Setter
  private String email = null;

  public Person id(Integer id) {
    this.id = id;
    return this;
  }

  public Person firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Person lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public Person address(String address) {
    this.address = address;
    return this;
  }

  public Person phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public Person zipCode(String zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public Person age(Integer age) {
    this.age = age;
    return this;
  }

  public Person city(String city) {
    this.city = city;
    return this;
  }

  public Person birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  public Person email(String email) {
    this.email = email;
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
    Person person = (Person) o;
    return 
//        Objects.equals(this.id, person.id) &&
        Objects.equals(this.firstName, person.firstName) &&
        Objects.equals(this.lastName, person.lastName) &&
        Objects.equals(this.address, person.address) &&
        Objects.equals(this.phoneNumber, person.phoneNumber) &&
        Objects.equals(this.zipCode, person.zipCode) &&
        Objects.equals(this.age, person.age) &&
        Objects.equals(this.city, person.city) &&
        Objects.equals(this.birthdate, person.birthdate) &&
        Objects.equals(this.email, person.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, phoneNumber, zipCode, age, city, birthdate, email);
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", zipCode=" + zipCode + ", age=" + age + ", city="
        + city + ", birthdate=" + birthdate + ", email=" + email + "]";
  }
}
