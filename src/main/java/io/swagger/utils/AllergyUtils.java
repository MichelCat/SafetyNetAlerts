package io.swagger.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.model.Allergy;

@Service
public class AllergyUtils {

  @Autowired
  private AllergyDao allergyDao;

  public MedicalRecordAllergyEntity allergyToMedicalRecordAllergyEntity(String allergy) {
    var medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
    medicalRecordAllergyEntity.setIdAlergy(allergyDao.findIdAllergyByName(allergy));
    return medicalRecordAllergyEntity;
  }

  public Allergy medicalRecordAllergyEntityToAllergy(MedicalRecordAllergyEntity medicalRecordAllergyEntity) {
    var allergy = new Allergy();
    allergy.setAllergy(allergyDao.allergyById(medicalRecordAllergyEntity.getIdAlergy()));
    return allergy;
  }
}
