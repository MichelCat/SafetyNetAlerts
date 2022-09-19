package io.swagger.model;

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
  private String medication = null;

  public Medication medication(String medication) {
    this.medication = medication;
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
    Medication medication = (Medication) o;
    return Objects.equals(this.medication, medication.medication);
  }

  @Override
  public int hashCode() {
    return Objects.hash(medication);
  }

  @Override
  public String toString() {
    return "Medication [medication=" + medication + "]";
  }
}
