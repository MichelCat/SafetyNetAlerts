package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

  private static SortedSet<MedicalRecordEntity> medicalRecordEntities = new TreeSet<>();

  // -----------------------------------------------------------------------------------------------
  @Override
  public void clearTable() {
    medicalRecordEntities.clear();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<MedicalRecordAllergyEntity> findAllergyEntityById(Integer idPerson) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity.getAllergies();
      }
    }
    return new ArrayList<MedicalRecordAllergyEntity>();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public List<MedicalRecordMedicationEntity> findMedicationEntityById(Integer idPerson) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity.getMedications();
      }
    }
    return new ArrayList<MedicalRecordMedicationEntity>();
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity findMedicalRecordEntityById(Integer idPerson) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        return medicalRecordEntity;
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity save(MedicalRecordEntity medicalRecordEntity) {
    medicalRecordEntities.add(medicalRecordEntity);
    return medicalRecordEntity;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicalRecordEntity update(MedicalRecordEntity medicalRecordEntity) {
    medicalRecordEntities.remove(medicalRecordEntity);
    medicalRecordEntities.add(medicalRecordEntity);
    return medicalRecordEntity;
  }
  
  // -----------------------------------------------------------------------------------------------
  @Override
  public void delete(Integer idPerson) {
    for (MedicalRecordEntity medicalRecordEntity : medicalRecordEntities) {
      if (medicalRecordEntity.getIdPerson().equals(idPerson)) {
        medicalRecordEntities.remove(medicalRecordEntity);
        return;
      }
    }
  }
}
