package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

  private static SortedSet<MedicalRecordEntity> medicalRecordEntities = new TreeSet<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public SortedSet<MedicalRecordEntity> getMedicalRecordEntities() {
    return medicalRecordEntities;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<MedicalRecordAllergyEntity> findAllergyEntityByName(String firstName, String lastName) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getLastName().equals(lastName)
          && medicalRecordEntity.getFirstName().equals(firstName)) {
        return medicalRecordEntity.getAllergies();
      }
    }
    return new ArrayList<MedicalRecordAllergyEntity>();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<MedicalRecordMedicationEntity> findMedicationEntityByName(String firstName, String lastName) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getLastName().equals(lastName)
          && medicalRecordEntity.getFirstName().equals(firstName)) {
        return medicalRecordEntity.getMedications();
      }
    }
    return new ArrayList<MedicalRecordMedicationEntity>();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity) {
    medicalRecordEntities.add(medicalRecordEntity);
    return medicalRecordEntity;
  }

}
