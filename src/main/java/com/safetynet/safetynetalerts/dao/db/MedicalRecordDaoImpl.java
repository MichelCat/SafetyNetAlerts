package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;

/**
 * FireStationDaoImpl manages the MedicalRecordEntity list
 * 
 * @author MC
 * @version 1.0
 */
@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDaoImpl.class);

  private static List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();

  /**
   * Clear MedicalRecordEntity list
   */
  @Override
  public void clearTable() {
    medicalRecordEntities.clear();
  }

  /**
   * Search allergies by person ID
   * 
   * @param idPerson Person ID
   * @return List of allergy
   */
  @Override
  public List<MedicalRecordAllergyEntity> findAllergyEntityById(Integer idPerson) {
    LOGGER.debug("Allergy Search Query by Person ID ({}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity.getAllergies();
      }
    }
    return new ArrayList<>();
  }

  /**
   * Search medications by person ID
   * 
   * @param idPerson Person ID
   * @return List of medication
   */
  @Override
  public List<MedicalRecordMedicationEntity> findMedicationEntityById(Integer idPerson) {
    LOGGER.debug("Medication Search Query by Person ID ({}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity.getMedications();
      }
    }
    return new ArrayList<>();
  }

  /**
   * Search medical record by person ID
   * 
   * @param idPerson Person ID
   * @return MedicalRecordEntity, if successful research, and null if not
   */
  @Override
  public MedicalRecordEntity findMedicalRecordEntityById(Integer idPerson) {
    LOGGER.debug("Medical record Search query by Person ID ({}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity;
      }
    }
    LOGGER.trace("Non-existent medical record (Person ID {}).", idPerson);
    return null;
  }

  /**
   * Add a medical record
   * 
   * @param newMedicalRecordEntity An object MedicalRecordEntity
   * @return MedicalRecordEntity, successful saved
   */
  @Override
  public MedicalRecordEntity save(MedicalRecordEntity newMedicalRecordEntity) {
    Integer idPerson = newMedicalRecordEntity.getIdPerson();

    LOGGER.debug("Query add medical record (Person ID {}).", idPerson);

    if (findMedicalRecordEntityById(idPerson) != null) {
      LOGGER.warn("Unable to add existing medical record (Person ID {}).", idPerson);
      return null;
    }

    if (medicalRecordEntities.add(newMedicalRecordEntity) == false) {
      LOGGER.error("Unable to add non-existent medical record (Person ID {}).", idPerson);
      return null;
    }
    return newMedicalRecordEntity;
  }

  /**
   * Update an existing medical record
   * 
   * @param newMedicalRecordEntity An object new MedicalRecordEntity
   * @return MedicalRecordEntity, successful updated
   */
  @Override
  public MedicalRecordEntity update(MedicalRecordEntity newMedicalRecordEntity) {
    Integer idPerson = newMedicalRecordEntity.getIdPerson();

    LOGGER.debug("Query update medical record (Person ID {}).", idPerson);

    MedicalRecordEntity medicalRecordEntity = findMedicalRecordEntityById(idPerson);

    if (medicalRecordEntity == null) {
      LOGGER.warn("Updated a non-existent medical record (Person ID {}).", idPerson);
      return null;
    }

    if (medicalRecordEntities.remove(medicalRecordEntity) == false) {
      LOGGER.error("Unable to remove the old medical record (Person ID {}).", idPerson);
      return null;
    }
    if (medicalRecordEntities.add(newMedicalRecordEntity) == false) {
      LOGGER.error("Unable to add new medical record (Person ID {}).", idPerson);
      return null;
    }
    return newMedicalRecordEntity;
  }

  /**
   * Delete a medical record
   * 
   * @param idPerson Person ID
   * @return True, successful deleted
   */
  @Override
  public boolean delete(Integer idPerson) {
    LOGGER.debug("Query delete medical record (Person ID {}).", idPerson);

    MedicalRecordEntity medicalRecordEntity = findMedicalRecordEntityById(idPerson);

    if (medicalRecordEntity == null) {
      LOGGER.warn("Removed a non-existent medical record (Person ID {}).", idPerson);
      return false;
    }

    if (medicalRecordEntities.remove(medicalRecordEntity) == false) {
      LOGGER.error("Unable to delete existing medical record (Person ID {}).", idPerson);
      return false;
    }
    return true;
  }
}
