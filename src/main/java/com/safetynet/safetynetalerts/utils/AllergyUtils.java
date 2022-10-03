package com.safetynet.safetynetalerts.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.model.Allergy;

@Service
public class AllergyUtils {

  @Autowired
  private AllergyDao allergyDao;

  public MedicalRecordAllergyEntity allergyToMedicalRecordAllergyEntity(String allergy) {
    var medicalRecordAllergyEntity = new MedicalRecordAllergyEntity();
    medicalRecordAllergyEntity.setIdAlergy(allergyDao.findIdAllergyByName(allergy).getId());
    return medicalRecordAllergyEntity;
  }

  public Allergy medicalRecordAllergyEntityToAllergy(MedicalRecordAllergyEntity medicalRecordAllergyEntity) {
    var allergy = new Allergy();
    allergy.setAllergy(allergyDao.allergyById(medicalRecordAllergyEntity.getIdAlergy()).getAllergy());
    return allergy;
  }
}
