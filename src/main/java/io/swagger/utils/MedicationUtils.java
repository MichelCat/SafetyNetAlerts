package io.swagger.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.model.Medication;

@Service
public class MedicationUtils {
  
  @Autowired
  private MedicationDao medicationDao;

  public MedicalRecordMedicationEntity medicationToMedicalRecordMedicationEntity(String medication) {
    if (medication.split(":").length != 2) {
      return null;
    }

    var medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
    medicalRecordMedicationEntity.setIdMedication(medicationDao.findIdMedicationByName(medication.split(":")[0]));
    medicalRecordMedicationEntity.setDosage(medication.split(":")[1]);
    return medicalRecordMedicationEntity;
  }

  public Medication medicalRecordMedicationEntityToMedication(MedicalRecordMedicationEntity medicalRecordMedicationEntity) {
    var medication = new Medication();
    medication.setMedication(medicationDao.medicationById(medicalRecordMedicationEntity.getIdMedication()) + ":" + medicalRecordMedicationEntity.getDosage());
    return medication;
  }
}
