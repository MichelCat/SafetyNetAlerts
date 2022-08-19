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
