package io.swagger.dao.json.entities;

import java.util.List;

public class SafetyNetJson {
  private List<PersonJson> persons;
  private List<FireStationJson> firestations;
  private List<MedicalRecordJson> medicalrecords;

  public List<PersonJson> getPersons() {
    return persons;
  }

  public void setPersons(List<PersonJson> persons) {
    this.persons = persons;
  }

  public List<FireStationJson> getFirestations() {
    return firestations;
  }

  public void setFirestations(List<FireStationJson> firestations) {
    this.firestations = firestations;
  }

  public List<MedicalRecordJson> getMedicalrecords() {
    return medicalrecords;
  }

  public void setMedicalrecords(List<MedicalRecordJson> medicalrecords) {
    this.medicalrecords = medicalrecords;
  }

}
