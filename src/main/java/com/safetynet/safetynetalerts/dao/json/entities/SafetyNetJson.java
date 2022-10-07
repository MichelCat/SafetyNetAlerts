package com.safetynet.safetynetalerts.dao.json.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * SafetyNetJson is JSON model
 * 
 * @author MC
 * @version 1.0
 */
public class SafetyNetJson {

  @Getter
  @Setter
  private List<PersonJson> persons = new ArrayList<>();

  @Getter
  @Setter
  private List<FireStationJson> firestations = new ArrayList<>();

  @Getter
  @Setter
  private List<MedicalRecordJson> medicalrecords = new ArrayList<>();

  /**
   * Add person to person list
   * 
   * @param personsItem Person to add
   * @return Person list
   */
  public SafetyNetJson addPersonsItem(PersonJson personsItem) {
    this.persons.add(personsItem);
    return this;
  }

  /**
   * Add fire station to fire station list
   * 
   * @param firestationsItem Fire station to add
   * @return Fire station list
   */
  public SafetyNetJson addFirestationsItem(FireStationJson firestationsItem) {
    this.firestations.add(firestationsItem);
    return this;
  }

  /**
   * Add medical record to medical record list
   * 
   * @param medicalrecordsItem Medical record to add
   * @return Medical record list
   */
  public SafetyNetJson addMedicalrecordsItem(MedicalRecordJson medicalrecordsItem) {
    this.medicalrecords.add(medicalrecordsItem);
    return this;
  }

  /**
   * Get the hash code for the object of class Method
   * 
   * @return Hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(firestations, medicalrecords, persons);
  }

  /**
   * Compare two objects
   * 
   * @param obj Object to compare
   * @return True if the objects are equal, and false if not.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SafetyNetJson other = (SafetyNetJson) obj;
    return Objects.equals(firestations, other.firestations)
        && Objects.equals(medicalrecords, other.medicalrecords)
        && Objects.equals(persons, other.persons);
  }
}
