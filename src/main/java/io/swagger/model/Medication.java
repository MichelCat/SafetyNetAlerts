package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Medication
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-21T15:04:05.884Z[GMT]")


public class Medication   {
  @JsonProperty("medication")
  private String medication = null;

  public Medication medication(String medication) {
    this.medication = medication;
    return this;
  }

  /**
   * Get medication
   * @return medication
   **/
  @Schema(description = "")
  
    public String getMedication() {
    return medication;
  }

  public void setMedication(String medication) {
    this.medication = medication;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class Medication {\n");
    
    sb.append("    medication: ").append(toIndentedString(medication)).append("\n");
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
