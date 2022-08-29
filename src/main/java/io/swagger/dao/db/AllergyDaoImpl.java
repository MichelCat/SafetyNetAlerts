package io.swagger.dao.db;

import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.AllergyEntity;

@Repository
public class AllergyDaoImpl implements AllergyDao {

  private static SortedSet<AllergyEntity> allergyEntities = new TreeSet<>();
  private static Integer allergySequence = 0;

  // -----------------------------------------------------------------------------------------------
  @Override
  public SortedSet<AllergyEntity> getAllergyEntities() {
    return allergyEntities;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String allergyById(Integer idAllergy) {
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getId().equals(idAllergy)) {
        return allergyEntity.getAllergy();
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public Integer findIdAllergyByName(String allergy) {
    for (AllergyEntity allergyEntity : allergyEntities) {
      if (allergyEntity.getAllergy().equalsIgnoreCase(allergy)) {
        return allergyEntity.getId();
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public AllergyEntity save(AllergyEntity allergyEntity) {
    if (!allergyEntities.contains(allergyEntity)) {
      ++allergySequence;
      allergyEntity.setId(allergySequence);
      allergyEntities.add(allergyEntity);
      return allergyEntity;
    }
    return null;
  }
  
}
