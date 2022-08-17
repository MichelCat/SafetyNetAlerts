package io.swagger.dao.db.entities;

import java.util.List;

public class MedicalRecordEntity implements Comparable<MedicalRecordEntity> {
  private Integer idPerson;
  private String firstName;
  private String lastName;
  private List<MedicalRecordAllergyEntity> allergies;
  private List<MedicalRecordMedicationEntity> medications;

  public Integer getIdPerson() {
    return idPerson;
  }

  public void setIdPerson(Integer idPerson) {
    this.idPerson = idPerson;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<MedicalRecordAllergyEntity> getAllergies() {
    return allergies;
  }

  public void setAllergies(List<MedicalRecordAllergyEntity> allergies) {
    this.allergies = allergies;
  }

  public List<MedicalRecordMedicationEntity> getMedications() {
    return medications;
  }

  public void setMedications(List<MedicalRecordMedicationEntity> medications) {
    this.medications = medications;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordEntity o) {
    if (this.firstName.compareTo(o.firstName) < 0)
      return -1;
    else if (this.firstName.compareTo(o.firstName) > 0)
      return 1;
    else if (this.lastName.compareTo(o.lastName) < 0)
      return -1;
    else if (this.lastName.compareTo(o.lastName) > 0)
      return 1;
    return 0;
  }
}
