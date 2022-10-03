package com.safetynet.safetynetalerts.dao.db.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class PersonEntity {
  @Getter @Setter
  private Integer id;

  @Getter @Setter
  private String firstName;

  @Getter @Setter
  private String lastName;

  @Getter @Setter
  private String address;

  @Getter @Setter
  private String city;

  @Getter @Setter
  private String zip;

  @Getter @Setter
  private String phoneNumber;

  @Getter @Setter
  private String email;

  @Getter @Setter
  private Date birthdate;

  // -----------------------------------------------------------------------------------------------
  public Integer getAge() {
    if (birthdate == null) {
      return (0);
    }
    LocalDate birthDate = new java.sql.Date(birthdate.getTime()).toLocalDate();
    return (Period.between(birthDate, LocalDate.now()).getYears());
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(address, birthdate, city, email, firstName, id, lastName, phoneNumber, zip);
  }

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
//        && Objects.equals(id, other.id)
        && lastName.equalsIgnoreCase(other.lastName)
        && Objects.equals(phoneNumber, other.phoneNumber)
        && Objects.equals(zip, other.zip);
  }
}