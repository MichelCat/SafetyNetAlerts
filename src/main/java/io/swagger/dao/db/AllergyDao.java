package io.swagger.dao.db;

import io.swagger.dao.db.entities.AllergyEntity;

public interface AllergyDao {
  void clearTable();
  String allergyById(Integer idAllergy);
  Integer findIdAllergyByName(String allergy);
  AllergyEntity save(AllergyEntity allergyEntity);
}
