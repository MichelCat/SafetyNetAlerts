package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;

@Repository
public class MedicalRecordMedicationDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;

  // -----------------------------------------------------------------------------------------------
  public List<MedicalRecordMedicationEntity> findMedicationEntityByName(String firstName, String lastName) {
    for (MedicalRecordEntity medicalRecordEntity : safetyNetDataBase.getMedicalRecordEntities()) {
      if (medicalRecordEntity.getLastName().equals(lastName)
          && medicalRecordEntity.getFirstName().equals(firstName)) {
        return medicalRecordEntity.getMedications();
      }
    }
    return new ArrayList<MedicalRecordMedicationEntity>();
  }
}
