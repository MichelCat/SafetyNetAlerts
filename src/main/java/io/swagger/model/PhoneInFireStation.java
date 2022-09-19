package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * PhoneInFireStation
 */
@Validated
public class PhoneInFireStation   {
  @JsonProperty("person")
  @Getter @Setter
  @Valid
  private Person person = null;

  public PhoneInFireStation person(Person person) {
    this.person = person;
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
    PhoneInFireStation phoneInFireStation = (PhoneInFireStation) o;
    return Objects.equals(this.person, phoneInFireStation.person);
  }

  @Override
  public int hashCode() {
    return Objects.hash(person);
  }

  @Override
  public String toString() {
    return "PhoneInFireStation [person=" + person + "]";
  }
}
