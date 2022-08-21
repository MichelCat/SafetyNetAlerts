package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Person;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PhoneInFireStation
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-20T22:36:24.460Z[GMT]")


public class PhoneInFireStation   {
  @JsonProperty("person")
  private Person person = null;

  public PhoneInFireStation person(Person person) {
    this.person = person;
    return this;
  }

  /**
   * Get person
   * @return person
   **/
  @Schema(description = "")
  
    @Valid
    public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneInFireStation phoneInFireStation = (PhoneInFireStation) o;
    return Objects.equals(this.person, phoneInFireStation.person);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhoneInFireStation {\n");
    
    sb.append("    person: ").append(toIndentedString(person)).append("\n");
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
