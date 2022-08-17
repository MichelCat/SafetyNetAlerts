package io.swagger.dao.db.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class PersonEntity implements Comparable<PersonEntity> {
  private Integer id;
  private String firstName;
  private String lastName;
  private String address;
  private String city;
  private String zip;
  private String phoneNumber;
  private String email;
  private Date birthdate;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

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
    if (this.lastName.compareTo(o.lastName) < 0)
      return -1;
    else if (this.lastName.compareTo(o.lastName) > 0)
      return 1;
    else if (this.firstName.compareTo(o.firstName) < 0)
      return -1;
    else if (this.firstName.compareTo(o.firstName) > 0)
      return 1;
    return 0;
  }
}
