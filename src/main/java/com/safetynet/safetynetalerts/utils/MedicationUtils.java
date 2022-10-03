package com.safetynet.safetynetalerts.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.model.Medication;

@Service
public class MedicationUtils {
  
  @Autowired
  private MedicationDao medicationDao;

  public MedicalRecordMedicationEntity medicationToMedicalRecordMedicationEntity(String medication) {
    if (medication.split(":").length != 2) {
      return null;
    }

    var medicalRecordMedicationEntity = new MedicalRecordMedicationEntity();
    MedicationEntity medicationEntity = medicationDao.findIdMedicationByName(medication.split(":")[0]);
    medicalRecordMedicationEntity.setIdMedication(medicationEntity.getId());
    medicalRecordMedicationEntity.setDosage(medication.split(":")[1]);
    return medicalRecordMedicationEntity;
  }

  public Medication medicalRecordMedicationEntityToMedication(MedicalRecordMedicationEntity medicalRecordMedicationEntity) {
    var medication = new Medication();
    MedicationEntity medicationEntity = medicationDao.medicationById(medicalRecordMedicationEntity.getIdMedication());
    medication.setMedication(medicationEntity.getMedication() + ":" + medicalRecordMedicationEntity.getDosage());
    return medication;
  }
}
