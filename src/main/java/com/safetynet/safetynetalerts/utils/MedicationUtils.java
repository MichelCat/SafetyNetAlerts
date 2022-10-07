package com.safetynet.safetynetalerts.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.model.Medication;

/**
 * MedicationUtils is an Medication object conversion utility class
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class MedicationUtils {

  @Autowired
  private MedicationDao medicationDao;

  /**
   * Conversion Medication name to MedicalRecordMedicationEntity
   * 
   * @param medication Medication name
   * @return MedicalRecordMedicationEntity
   */
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

  /**
   * Conversion MedicalRecordMedicationEntity to Medication
   * 
   * @param medicalRecordMedicationEntity Object MedicalRecordMedicationEntity
   * @return Medication
   */
  public Medication medicalRecordMedicationEntityToMedication(MedicalRecordMedicationEntity medicalRecordMedicationEntity) {
    var medication = new Medication();
    MedicationEntity medicationEntity = medicationDao.medicationById(medicalRecordMedicationEntity.getIdMedication());
    medication.setMedication(medicationEntity.getMedication() + ":" + medicalRecordMedicationEntity.getDosage());
    return medication;
  }
}
