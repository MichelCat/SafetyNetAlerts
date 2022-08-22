package io.swagger.dao.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.AllergyEntity;
import io.swagger.dao.db.entities.MedicationEntity;

@Repository
public class AllergyDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;

  // -----------------------------------------------------------------------------------------------
  public String allergyById(Integer idAllergy) {
    for (AllergyEntity allergyEntity : safetyNetDataBase.getAllergyEntities()) {
      if (allergyEntity.getId().equals(idAllergy)) {
        return allergyEntity.getAllergy();
      }
    }
    return "";
  }
}
