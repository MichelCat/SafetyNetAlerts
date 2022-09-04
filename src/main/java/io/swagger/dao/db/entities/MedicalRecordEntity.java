package io.swagger.dao.db.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class MedicalRecordEntity implements Comparable<MedicalRecordEntity> {
  @Getter @Setter
  private Integer idPerson;
  
  @Getter @Setter
  private List<MedicalRecordAllergyEntity> allergies;
  
  @Getter @Setter
  private List<MedicalRecordMedicationEntity> medications;

  // -----------------------------------------------------------------------------------------------
  @Override
  public int compareTo(MedicalRecordEntity o) {
    return idPerson.compareTo(o.idPerson);
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String toString() {
    return "MedicalRecordEntity [idPerson=" + idPerson + ", allergies=" + allergies + ", medications=" + medications + "]";
  }
}
