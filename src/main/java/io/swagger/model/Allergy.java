package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Allergy
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-21T15:04:05.884Z[GMT]")


public class Allergy   {
  @JsonProperty("allergy")
  private String allergy = null;

  public Allergy allergy(String allergy) {
    this.allergy = allergy;
    return this;
  }

  /**
   * Get allergy
   * @return allergy
   **/
  @Schema(description = "")
  
    public String getAllergy() {
    return allergy;
  }

  public void setAllergy(String allergy) {
    this.allergy = allergy;
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
    StringBuilder sb = new StringBuilder();
    sb.append("class Allergy {\n");
    
    sb.append("    allergy: ").append(toIndentedString(allergy)).append("\n");
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
