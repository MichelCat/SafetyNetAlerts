package com.safetynet.safetynetalerts.dao.db;

import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;

/**
 * MedicationDao is interface that manages the MedicationEntity list
 * 
 * @author MC
 * @version 1.0
 */
public interface MedicationDao {
  void clearTable();
  MedicationEntity medicationById(Integer idMedication);
  MedicationEntity findIdMedicationByName(String medication);
  MedicationEntity save(MedicationEntity medicationEntity);
}
