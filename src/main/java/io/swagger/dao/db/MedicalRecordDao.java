package io.swagger.dao.db;

import java.util.List;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;

public interface MedicalRecordDao {
  void clearTable();
  List<MedicalRecordAllergyEntity> findAllergyEntityById(Integer idPerson);
  List<MedicalRecordMedicationEntity> findMedicationEntityById(Integer idPerson);
  MedicalRecordEntity findMedicalRecordEntityById(Integer idPerson);
  MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity);
  MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity);
  void delete(Integer idPerson);
}
