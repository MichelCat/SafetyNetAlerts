package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PersonInFireStation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-27T22:35:44.562Z[GMT]")


public class PersonInFireStation   {
  @JsonProperty("adultsCount")
  private Integer adultsCount = null;

  @JsonProperty("childrenCount")
  private Integer childrenCount = null;

  @JsonProperty("persons")
  @Valid
  private List<Person> persons = null;

  public PersonInFireStation adultsCount(Integer adultsCount) {
    this.adultsCount = adultsCount;
    return this;
  }

  /**
   * Get adultsCount
   * @return adultsCount
   **/
  @Schema(description = "")
  
    public Integer getAdultsCount() {
    return adultsCount;
  }

  public void setAdultsCount(Integer adultsCount) {
    this.adultsCount = adultsCount;
  }

  public PersonInFireStation childrenCount(Integer childrenCount) {
    this.childrenCount = childrenCount;
    return this;
  }

  /**
   * Get childrenCount
   * @return childrenCount
   **/
  @Schema(description = "")
  
    public Integer getChildrenCount() {
    return childrenCount;
  }

  public void setChildrenCount(Integer childrenCount) {
    this.childrenCount = childrenCount;
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

  /**
   * Get persons
   * @return persons
   **/
  @Schema(description = "")
      @Valid
    public List<Person> getPersons() {
    return persons;
  }

  public void setPersons(List<Person> persons) {
    this.persons = persons;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonInFireStation {\n");
    
    sb.append("    adultsCount: ").append(toIndentedString(adultsCount)).append("\n");
    sb.append("    childrenCount: ").append(toIndentedString(childrenCount)).append("\n");
    sb.append("    persons: ").append(toIndentedString(persons)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
