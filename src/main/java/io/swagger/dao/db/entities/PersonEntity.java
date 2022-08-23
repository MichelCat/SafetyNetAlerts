package io.swagger.dao.db.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class PersonEntity implements Comparable<PersonEntity> {
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
  public int compareTo(PersonEntity o) {
    if (getLastName().equals(o.getLastName())) {
      return getFirstName().compareTo(o.getFirstName());
    } else {
      return getLastName().compareTo(o.getLastName());
    }
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "PersonEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city=" + city + ", zip=" + zip + ", phoneNumber=" + phoneNumber + ", email="
        + email + ", birthdate=" + birthdate + "]";
  }
}
