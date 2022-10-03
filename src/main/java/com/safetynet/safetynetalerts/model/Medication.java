package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Medication
 */
@Validated
public class Medication   {
  @JsonProperty("medication")
  @Getter @Setter
  private String medication;

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    var medication = (Medication) o;
    return Objects.equals(this.medication, medication.medication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(medication);
  }
}