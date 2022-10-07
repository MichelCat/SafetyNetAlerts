package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * PhoneInFireStation is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
public class PhoneInFireStation {
  @JsonProperty("person")
  @Getter
  @Setter
  @Valid
  private Person person;

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
    var phoneInFireStation = (PhoneInFireStation) o;
    return Objects.equals(this.person, phoneInFireStation.person);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(person);
  }
}
