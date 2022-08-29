package io.swagger.dao.db;

import java.util.SortedSet;
import io.swagger.dao.db.entities.AllergyEntity;

public interface AllergyDao {
  SortedSet<AllergyEntity> getAllergyEntities();
  String allergyById(Integer idAllergy);
  Integer findIdAllergyByName(String allergy);
  AllergyEntity save(AllergyEntity allergyEntity);
}
