package com.safetynet.safetynetalerts.dao.db;

import java.util.List;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;

public interface MedicalRecordDao {
  void clearTable();
  List<MedicalRecordAllergyEntity> findAllergyEntityById(Integer idPerson);
  List<MedicalRecordMedicationEntity> findMedicationEntityById(Integer idPerson);
  MedicalRecordEntity findMedicalRecordEntityById(Integer idPerson);
  MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity);
  MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity);
  boolean delete(Integer idPerson);
}
