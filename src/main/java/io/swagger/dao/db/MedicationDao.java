package io.swagger.dao.db;

import io.swagger.dao.db.entities.MedicationEntity;

public interface MedicationDao {
  void clearTable();
  String medicationById(Integer idMedication);
  Integer findIdMedicationByName(String medication);
  MedicationEntity save(MedicationEntity medicationEntity);
}
