package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
import lombok.Getter;
import lombok.Setter;

/**
 * Allergy
 */
@Validated
public class Allergy   {
  @JsonProperty("allergy")
  @Getter @Setter
  private String allergy = null;

  public Allergy allergy(String allergy) {
    this.allergy = allergy;
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
    Allergy allergy = (Allergy) o;
    return Objects.equals(this.allergy, allergy.allergy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(allergy);
  }

  @Override
  public String toString() {
    return "Allergy [allergy=" + allergy + "]";
  }
}
