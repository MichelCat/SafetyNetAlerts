package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;

/**
 * AllergyDaoImpl manages the AllergyEntity list
 * 
 * @author MC
 * @version 1.0
 */
@Repository
public class AllergyDaoImpl implements AllergyDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(AllergyDaoImpl.class);

  private static List<AllergyEntity> allergyEntities = new ArrayList<>();
  private static Integer allergySequence = 0;

  /**
   * Clear AllergyEntity list
   */
  @Override
  public void clearTable() {
    allergySequence = 0;
    allergyEntities.clear();
  }

  /**
   * Allergy search by allergy ID
   * 
   * @param idAllergy Allergy ID
   * @return AllergyEntity, if successful research, and null if not
   */
  @Override
  public AllergyEntity allergyById(Integer idAllergy) {
    LOGGER.debug("Allergy search query by allergy identifier ({}).", idAllergy);
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getId().equals(idAllergy)) {
        return allergyEntity;
      }
    }
    LOGGER.trace("Non-existent allergy identifier ({}).", idAllergy);
    return null;
  }

  /**
   * Allergy search by allergy name
   * 
   * @param allergy Allergy name
   * @return AllergyEntity, if successful research, and null if not
   */
  @Override
  public AllergyEntity findIdAllergyByName(String allergy) {
    LOGGER.debug("Allergy Search query by allergy ({}).", allergy);
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getAllergy().equalsIgnoreCase(allergy)) {
        return allergyEntity;
      }
    }
    LOGGER.trace("Non-existent allergy ({}).", allergy);
    return null;
  }

  /**
   * Add a allergy
   * 
   * @param newAllergyEntity An object AllergyEntity
   * @return AllergyEntity, successful saved
   */
  @Override
  public AllergyEntity save(AllergyEntity newAllergyEntity) {
    String newAllergy = newAllergyEntity.getAllergy();

    LOGGER.debug("Request to add an allergy ({}).", newAllergy);

    if (findIdAllergyByName(newAllergy) != null) {
      LOGGER.warn("Unable to add an existing allergy ({}).", newAllergy);
      return null;
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
