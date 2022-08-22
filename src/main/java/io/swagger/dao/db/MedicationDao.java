package io.swagger.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicationEntity;

@Repository
public class MedicationDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;
  
  // -----------------------------------------------------------------------------------------------
  public String medicationById(Integer idMedication) {
    for (MedicationEntity medicationEntity : safetyNetDataBase.getMedicationEntities()) {
      if (medicationEntity.getId().equals(idMedication)) {
        return medicationEntity.getMedication();
      }
    }
    return "";
  }
}
