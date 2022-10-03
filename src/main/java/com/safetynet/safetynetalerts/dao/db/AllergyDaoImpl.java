package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;

@Repository
public class AllergyDaoImpl implements AllergyDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(AllergyDaoImpl.class);
  
  private static List<AllergyEntity> allergyEntities = new ArrayList<>();
  private static Integer allergySequence = 0;

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    allergySequence = 0;
    allergyEntities.clear();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public AllergyEntity allergyById(Integer idAllergy) {
    LOGGER.debug("Search query by allergy identifier ({}).", idAllergy);
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getId().equals(idAllergy)) {
        return allergyEntity;
      }
    }
    LOGGER.warn("Non-existent allergy identifier ({}).", idAllergy);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public AllergyEntity findIdAllergyByName(String allergy) {
    LOGGER.debug("Search query by allergy ({}).", allergy);
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getAllergy().equalsIgnoreCase(allergy)) {
        return allergyEntity;
      }
    }
//    LOGGER.warn("Non-existent allergy ({}).", allergy);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public AllergyEntity save(AllergyEntity newAllergyEntity) {
    String newAllergy = newAllergyEntity.getAllergy();
    
    LOGGER.debug("Request to add an allergy ({}).", newAllergy);
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getAllergy().equalsIgnoreCase(newAllergy)) {
        LOGGER.warn("Unable to add an existing allergy ({}).", newAllergy);
        return null;
      }
    }
    ++allergySequence;
    newAllergyEntity.setId(allergySequence);
    if (allergyEntities.add(newAllergyEntity) == false) {
      LOGGER.error("Unable to add non-existent allergy ({}).", newAllergy);
      return null;
    }
    return newAllergyEntity;
  }
}
