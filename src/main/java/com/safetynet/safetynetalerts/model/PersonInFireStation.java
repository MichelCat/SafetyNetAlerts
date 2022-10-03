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
 * PersonInFireStation
 */
@Validated
public class PersonInFireStation   {
  @JsonProperty("adultsCount")
  @Getter @Setter
  private Integer adultsCount;

  @JsonProperty("childrenCount")
  @Getter @Setter
  private Integer childrenCount;

  @JsonProperty("persons")
  @Valid
  @Getter @Setter
  private List<Person> persons = new ArrayList<>();

  public PersonInFireStation addPersonsItem(Person personsItem) {
    this.persons.add(personsItem);
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
    var personInFireStation = (PersonInFireStation) o;
    return Objects.equals(this.adultsCount, personInFireStation.adultsCount) &&
        Objects.equals(this.childrenCount, personInFireStation.childrenCount) &&
        Objects.equals(this.persons, personInFireStation.persons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adultsCount, childrenCount, persons);
  }
}
