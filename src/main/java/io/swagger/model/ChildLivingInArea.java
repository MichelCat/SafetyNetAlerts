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
 * ChildLivingInArea
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-08-09T21:09:55.880Z[GMT]")


public class ChildLivingInArea   {
  @JsonProperty("Identity")
  private Person identity = null;

  @JsonProperty("familyMembers")
  @Valid
  private List<Person> familyMembers = null;

  public ChildLivingInArea identity(Person identity) {
    this.identity = identity;
    return this;
  }

  /**
   * Get identity
   * @return identity
   **/
  @Schema(description = "")
  
    @Valid
    public Person getIdentity() {
    return identity;
  }

  public void setIdentity(Person identity) {
    this.identity = identity;
  }

  public ChildLivingInArea familyMembers(List<Person> familyMembers) {
    this.familyMembers = familyMembers;
    return this;
  }

  public ChildLivingInArea addFamilyMembersItem(Person familyMembersItem) {
    if (this.familyMembers == null) {
      this.familyMembers = new ArrayList<Person>();
    }
    this.familyMembers.add(familyMembersItem);
    return this;
  }

  /**
   * Get familyMembers
   * @return familyMembers
   **/
  @Schema(description = "")
      @Valid
    public List<Person> getFamilyMembers() {
    return familyMembers;
  }

  public void setFamilyMembers(List<Person> familyMembers) {
    this.familyMembers = familyMembers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ChildLivingInArea childLivingInArea = (ChildLivingInArea) o;
    return Objects.equals(this.identity, childLivingInArea.identity) &&
        Objects.equals(this.familyMembers, childLivingInArea.familyMembers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identity, familyMembers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ChildLivingInArea {\n");
    
    sb.append("    identity: ").append(toIndentedString(identity)).append("\n");
    sb.append("    familyMembers: ").append(toIndentedString(familyMembers)).append("\n");
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
