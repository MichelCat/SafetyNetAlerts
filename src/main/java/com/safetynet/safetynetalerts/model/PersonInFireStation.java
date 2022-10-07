package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * PersonInFireStation is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public class PersonInFireStation {
  @JsonProperty("adultsCount")
  @Getter
  @Setter
  private Integer adultsCount;

  @JsonProperty("childrenCount")
  @Getter
  @Setter
  private Integer childrenCount;

  @JsonProperty("persons")
  @Valid
  @Getter
  @Setter
  private List<Person> persons = new ArrayList<>();

  /**
   * Add person to person list
   * 
   * @param personsItem Person to add
   * @return Person list
   */
  public PersonInFireStation addPersonsItem(Person personsItem) {
    this.persons.add(personsItem);
    return this;
  }

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
    var personInFireStation = (PersonInFireStation) o;
    return Objects.equals(this.adultsCount, personInFireStation.adultsCount)
        && Objects.equals(this.childrenCount, personInFireStation.childrenCount)
        && Objects.equals(this.persons, personInFireStation.persons);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(adultsCount, childrenCount, persons);
  }
}
