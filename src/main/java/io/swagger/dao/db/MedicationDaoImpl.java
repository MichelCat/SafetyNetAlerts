package io.swagger.dao.db;

import java.util.SortedSet;
import java.util.TreeSet;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.MedicationEntity;

@Repository
public class MedicationDaoImpl implements MedicationDao {

  private static SortedSet<MedicationEntity> medicationEntities = new TreeSet<>();
  private static Integer medicamentSequence = 0;

  // -----------------------------------------------------------------------------------------------
  @Override
  public SortedSet<MedicationEntity> getMedicationEntities() {
    return medicationEntities;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public String medicationById(Integer idMedication) {
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getId().equals(idMedication)) {
        return medicationEntity.getMedication();
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public Integer findIdMedicationByName(String medication) {
    for (MedicationEntity medicationEntity : medicationEntities) {
      if (medicationEntity.getMedication().equalsIgnoreCase(medication)) {
        return medicationEntity.getId();
      }
    }
    return null;
  }

  // -----------------------------------------------------------------------------------------------
  @Override
  public MedicationEntity save(MedicationEntity medicationEntity) {
    if (!medicationEntities.contains(medicationEntity)) {
      ++medicamentSequence;
      medicationEntity.setId(medicamentSequence);
      medicationEntities.add(medicationEntity);
      return medicationEntity;
    }
    return null;
  }

}
