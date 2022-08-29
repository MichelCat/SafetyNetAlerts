package io.swagger.dao.db;

import java.util.List;
import java.util.SortedSet;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;

public interface MedicalRecordDao {
  SortedSet<MedicalRecordEntity> getMedicalRecordEntities();
  List<MedicalRecordAllergyEntity> findAllergyEntityByName(String firstName, String lastName);
  List<MedicalRecordMedicationEntity> findMedicationEntityByName(String firstName, String lastName);
  MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity);
}
