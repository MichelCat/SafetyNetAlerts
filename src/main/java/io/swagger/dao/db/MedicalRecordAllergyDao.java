package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;

@Repository
public class MedicalRecordAllergyDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;

  // -----------------------------------------------------------------------------------------------
  public List<MedicalRecordAllergyEntity> findAllergyEntityByName(String firstName, String lastName) {
    for (MedicalRecordEntity medicalRecordEntity : safetyNetDataBase.getMedicalRecordEntities()) {
      if (medicalRecordEntity.getLastName().equals(lastName)
          && medicalRecordEntity.getFirstName().equals(firstName)) {
        return medicalRecordEntity.getAllergies();
      }
    }
    return new ArrayList<MedicalRecordAllergyEntity>();
  }
}
