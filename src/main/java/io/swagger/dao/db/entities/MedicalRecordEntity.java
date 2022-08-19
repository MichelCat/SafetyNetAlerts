package io.swagger.dao.db.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class MedicalRecordEntity implements Comparable<MedicalRecordEntity> {
  @Getter @Setter
  private Integer idPerson;
  
  @Getter @Setter
  private String firstName;
  
  @Getter @Setter
  private String lastName;
  
  @Getter @Setter
  private List<MedicalRecordAllergyEntity> allergies;
  
  @Getter @Setter
  private List<MedicalRecordMedicationEntity> medications;

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
