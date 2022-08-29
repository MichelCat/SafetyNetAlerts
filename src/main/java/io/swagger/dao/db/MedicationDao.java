package io.swagger.dao.db;

import java.util.SortedSet;
import io.swagger.dao.db.entities.MedicationEntity;

public interface MedicationDao {
  SortedSet<MedicationEntity> getMedicationEntities();
  String medicationById(Integer idMedication);
  Integer findIdMedicationByName(String medication);
  MedicationEntity save(MedicationEntity medicationEntity);
}
