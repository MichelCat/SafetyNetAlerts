package io.swagger.model;

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
  private Integer adultsCount = null;

  @JsonProperty("childrenCount")
  @Getter @Setter
  private Integer childrenCount = null;

  @JsonProperty("persons")
  @Valid
  @Getter @Setter
  private List<Person> persons = null;

  public PersonInFireStation adultsCount(Integer adultsCount) {
    this.adultsCount = adultsCount;
    return this;
  }

  public PersonInFireStation childrenCount(Integer childrenCount) {
    this.childrenCount = childrenCount;
    return this;
  }

  public PersonInFireStation persons(List<Person> persons) {
    this.persons = persons;
    return this;
  }

  public PersonInFireStation addPersonsItem(Person personsItem) {
    if (this.persons == null) {
      this.persons = new ArrayList<Person>();
    }
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
    PersonInFireStation personInFireStation = (PersonInFireStation) o;
    return Objects.equals(this.adultsCount, personInFireStation.adultsCount) &&
        Objects.equals(this.childrenCount, personInFireStation.childrenCount) &&
        Objects.equals(this.persons, personInFireStation.persons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(adultsCount, childrenCount, persons);
  }

  @Override
  public String toString() {
    return "PersonInFireStation [adultsCount=" + adultsCount + ", childrenCount=" + childrenCount + ", persons=" + persons + "]";
  }
}
