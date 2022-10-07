package com.safetynet.safetynetalerts.dao.db;

import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;

/**
 * AllergyDao is interface that manages the AllergyEntity list
 * 
 * @author MC
 * @version 1.0
 */
public interface AllergyDao {
  void clearTable();
  AllergyEntity allergyById(Integer idAllergy);
  AllergyEntity findIdAllergyByName(String allergy);
  AllergyEntity save(AllergyEntity allergyEntity);
}
