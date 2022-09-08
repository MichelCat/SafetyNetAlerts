package io.swagger.data;

import io.swagger.dao.db.entities.MedicationEntity;
import io.swagger.model.Medication;

public class MedicationData {

  // -----------------------------------------------------------------------------------------------
  // Aznol
  // -----------------------------------------------------------------------------------------------
  public static Medication getMedicationAznol() {
    Medication medication = new Medication();
    medication.setMedication("aznol");
    return(medication);
  }
  
  public static MedicationEntity getMedicationEntityAznol() {
    MedicationEntity medicationEntity = new MedicationEntity();
    medicationEntity.setId(1);
    medicationEntity.setMedication("aznol");
    return(medicationEntity);
  }

}
