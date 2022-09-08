package io.swagger.data;

import io.swagger.dao.db.entities.AllergyEntity;
import io.swagger.model.Allergy;

public class AllergyData {

  // -----------------------------------------------------------------------------------------------
  // Nillacilan
  // -----------------------------------------------------------------------------------------------
  public static Allergy getAllergyNillacilan() {
    Allergy allergy = new Allergy();
    allergy.setAllergy("nillacilan");
    return(allergy);
  }
  
  public static AllergyEntity getAllergyEntityNillacilan() {
    AllergyEntity allergyEntity = new AllergyEntity();
    allergyEntity.setId(1);
    allergyEntity.setAllergy("nillacilan");
    return(allergyEntity);
  }

}
