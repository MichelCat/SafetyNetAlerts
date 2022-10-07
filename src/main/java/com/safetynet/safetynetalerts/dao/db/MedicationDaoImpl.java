package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;

/**
 * MedicationDaoImpl manages the MedicationEntity list
 * 
 * @author MC
 * @version 1.0
 */
@Repository
public class MedicationDaoImpl implements MedicationDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicationDaoImpl.class);

  private static List<MedicationEntity> medicationEntities = new ArrayList<>();
  private static Integer medicamentSequence = 0;

  /**
   * Clear MedicationEntity list
   */
  @Override
  public void clearTable() {
    medicamentSequence = 0;
    medicationEntities.clear();
  }

  /**
   * Medication search by medication ID
   * 
   * @param idMedication Medication ID
   * @return MedicationEntity, if successful research, and null if not
   */
  @Override
  public MedicationEntity medicationById(Integer idMedication) {
    LOGGER.debug("Medication search query by medication identifier ({}).", idMedication);
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getId().equals(idMedication)) {
        return medicationEntity;
      }
    }
    LOGGER.trace("Non-existent medication identifier ({}).", idMedication);
    return null;
  }

  /**
   * Medication search by medication name
   * 
   * @param medication Medication name
   * @return MedicationEntity, if successful research, and null if not
   */
  @Override
  public MedicationEntity findIdMedicationByName(String medication) {
    LOGGER.debug("Medication Search query by medication ({}).", medication);
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getMedication().equalsIgnoreCase(medication)) {
        return medicationEntity;
      }
    }
    LOGGER.trace("Non-existent medication ({}).", medication);
    return null;
  }

  /**
   * Add a medication
   * 
   * @param newMedicationEntity An object MedicationEntity
   * @return MedicationEntity, successful saved
   */
  @Override
  public MedicationEntity save(MedicationEntity newMedicationEntity) {
    String newMedication = newMedicationEntity.getMedication();

    LOGGER.debug("Request to add an medication ({}).", newMedication);

    if (findIdMedicationByName(newMedication) != null) {
      LOGGER.warn("Unable to add an existing medication ({}).", newMedication);
      return null;
    }

    ++medicamentSequence;
    newMedicationEntity.setId(medicamentSequence);
    if (medicationEntities.add(newMedicationEntity) == false) {
      LOGGER.error("Unable to add non-existent medication ({}).", newMedication);
      return null;
    }
    return newMedicationEntity;
  }
}
