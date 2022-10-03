package com.safetynet.safetynetalerts.dao.json.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

public class SafetyNetJson {
  
  @Getter @Setter
  private List<PersonJson> persons = new ArrayList<>();
  
  @Getter @Setter
  private List<FireStationJson> firestations = new ArrayList<>();
  
  @Getter @Setter
  private List<MedicalRecordJson> medicalrecords = new ArrayList<>();
  
  public SafetyNetJson addPersonsItem(PersonJson personsItem) {
    this.persons.add(personsItem);
    return this;
  }
  
  public SafetyNetJson addFirestationsItem(FireStationJson firestationsItem) {
    this.firestations.add(firestationsItem);
    return this;
  }
  
  public SafetyNetJson addMedicalrecordsItem(MedicalRecordJson medicalrecordsItem) {
    this.medicalrecords.add(medicalrecordsItem);
    return this;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int hashCode() {
    return Objects.hash(firestations, medicalrecords, persons);
  }

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
