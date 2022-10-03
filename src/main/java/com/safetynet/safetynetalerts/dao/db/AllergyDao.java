package com.safetynet.safetynetalerts.dao.db;

import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;

public interface AllergyDao {
  void clearTable();
  AllergyEntity allergyById(Integer idAllergy);
  AllergyEntity findIdAllergyByName(String allergy);
  AllergyEntity save(AllergyEntity allergyEntity);
}
