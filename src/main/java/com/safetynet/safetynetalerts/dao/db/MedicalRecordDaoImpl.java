package com.safetynet.safetynetalerts.dao.db;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

  private static final Logger LOGGER = LoggerFactory.getLogger(MedicalRecordDaoImpl.class);

  private static List<MedicalRecordEntity> medicalRecordEntities = new ArrayList<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    medicalRecordEntities.clear();
  }

  // -----------------------------------------------------------------------------------------------
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

  // -----------------------------------------------------------------------------------------------
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

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity findMedicalRecordEntityById(Integer idPerson) {
    LOGGER.debug("Search query by Person ID ({}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity;
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity save(MedicalRecordEntity newMedicalRecordEntity) {
    Integer idPerson = newMedicalRecordEntity.getIdPerson();
    
    LOGGER.debug("Query add medical record (Person ID {}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        LOGGER.warn("Unable to add existing medical record (Person ID {}).", idPerson);
        return null;
      }
    }
    if (medicalRecordEntities.add(newMedicalRecordEntity) == false) {
      LOGGER.error("Unable to add non-existent medical record (Person ID {}).", idPerson);
      return null;
    }
    return newMedicalRecordEntity;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity update(MedicalRecordEntity newMedicalRecordEntity) {
    Integer idPerson = newMedicalRecordEntity.getIdPerson();
    
    LOGGER.debug("Query update medical record (Person ID {}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
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
    }
    LOGGER.warn("Updated a non-existent medical record (Person ID {}).", idPerson);
    return null;
  }
  
  // -----------------------------------------------------------------------------------------------
  @Override
  public boolean delete(Integer idPerson) {
    LOGGER.debug("Query delete medical record (Person ID {}).", idPerson);
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        if (medicalRecordEntities.remove(medicalRecordEntity) == false) {
          LOGGER.error("Unable to delete existing medical record (Person ID {}).", idPerson);
          return false;
        }
        return true;
      }
    }
    LOGGER.warn("Removed a non-existent medical record (Person ID {}).", idPerson);
    return false;
  }
}
