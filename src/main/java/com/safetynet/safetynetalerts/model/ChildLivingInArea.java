package com.safetynet.safetynetalerts.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;

/**
 * ChildLivingInArea is business model
 * 
 * @author MC
 * @version 1.0
 */
@Validated
@Getter
@Setter
public class ChildLivingInArea {
  @JsonProperty("child")
  @Valid
  private Person child;

  @JsonProperty("familyMembers")
  @Valid
  private List<Person> familyMembers = new ArrayList<>();

  /**
   * Add person to person list
   * 
   * @param familyMembersItem Person to add
   * @return Person list
   */
  public ChildLivingInArea addFamilyMembersItem(Person familyMembersItem) {
    this.familyMembers.add(familyMembersItem);
    return this;
  }

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
    var childLivingInArea = (ChildLivingInArea) o;
    return Objects.equals(this.child, childLivingInArea.child)
        && Objects.equals(this.familyMembers, childLivingInArea.familyMembers);
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(child, familyMembers);
  }
}
