package com.safetynet.safetynetalerts.dao.db.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * PersonEntity is Entity model
 * 
 * @author MC
 * @version 1.0
 */
@Getter
@Setter
public class PersonEntity {
  private Integer id;
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private String zip;
  private String phoneNumber;
  private String email;
  private Date birthdate;

  /**
   * Calculate the person's age
   * 
   * @return Person's age
   */
  public Integer getAge() {
    if (birthdate == null) {
      return (0);
    }
    LocalDate birthDate = new java.sql.Date(birthdate.getTime()).toLocalDate();
    return (Period.between(birthDate, LocalDate.now()).getYears());
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, birthdate, city, email, firstName, id, lastName, phoneNumber, zip);
  }

  /**
   * Compare two objects
   * 
   * @param obj Object to compare
   * @return True if the objects are equal, and false if not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    var other = (PersonEntity) obj;
    return Objects.equals(address, other.address)
        && Objects.equals(birthdate, other.birthdate)
        && Objects.equals(city, other.city)
        && Objects.equals(email, other.email)
        && firstName.equalsIgnoreCase(other.firstName)
    // && Objects.equals(id, other.id)
        && lastName.equalsIgnoreCase(other.lastName)
        && Objects.equals(phoneNumber, other.phoneNumber)
        && Objects.equals(zip, other.zip);
  }
}
