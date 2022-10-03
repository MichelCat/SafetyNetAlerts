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
 * ChildLivingInArea
 */
@Validated
public class ChildLivingInArea   {
  @JsonProperty("child")
  @Valid
  @Getter @Setter
  private Person child;

  @JsonProperty("familyMembers")
  @Valid
  @Getter @Setter
  private List<Person> familyMembers = new ArrayList<>();

  public ChildLivingInArea addFamilyMembersItem(Person familyMembersItem) {
    this.familyMembers.add(familyMembersItem);
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
    var childLivingInArea = (ChildLivingInArea) o;
    return Objects.equals(this.child, childLivingInArea.child) &&
        Objects.equals(this.familyMembers, childLivingInArea.familyMembers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(child, familyMembers);
  }
}
