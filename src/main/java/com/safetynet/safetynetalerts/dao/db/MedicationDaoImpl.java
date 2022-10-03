package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;

@Repository
public class MedicationDaoImpl implements MedicationDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicationDaoImpl.class);

  private static List<MedicationEntity> medicationEntities = new ArrayList<>();
  private static Integer medicamentSequence = 0;

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    medicamentSequence = 0;
    medicationEntities.clear();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicationEntity medicationById(Integer idMedication) {
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getId().equals(idMedication)) {
        return medicationEntity;
      }
    }
    LOGGER.warn("Non-existent medication identifier ({}).", idMedication);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicationEntity findIdMedicationByName(String medication) {
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getMedication().equalsIgnoreCase(medication)) {
        return medicationEntity;
      }
    }
//    LOGGER.warn("Non-existent medication ({}).", medication);
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicationEntity save(MedicationEntity newMedicationEntity) {
    String newMedication = newMedicationEntity.getMedication();
    
    LOGGER.debug("Request to add an medication ({}).", newMedication);
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getMedication().equalsIgnoreCase(newMedication)) {
        LOGGER.warn("Unable to add an existing medication ({}).", newMedication);
        return null;
      }
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
