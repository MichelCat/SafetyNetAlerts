package com.safetynet.safetynetalerts.utils;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordAllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordMedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Medication;

@Service
public class MedicalRecordUtils {

  @Autowired
  private AllergyUtils allergyUtils;
  @Autowired
  private MedicationUtils medicationUtils;
  @Autowired
  private DateUtils dateUtils;
  
  public MedicalRecord medicalRecordEntityToMedicalRecord(MedicalRecordEntity medicalRecordEntity, PersonEntity personEntity) {
    var medicalRecord = new MedicalRecord();
    medicalRecord.setId(medicalRecordEntity.getIdPerson());
    medicalRecord.setFirstName(personEntity.getFirstName());
    medicalRecord.setLastName(personEntity.getLastName());
    medicalRecord.setBirthdate(dateUtils.dateToStringDDMMYYYYConversion(personEntity.getBirthdate()));
    
    List<Allergy> allergies = medicalRecordAllergyEntityListToAllergyList(medicalRecordEntity.getAllergies());
    medicalRecord.setAllergies(allergies);

    List<Medication> medications = medicalRecordMedicationEntityListToMedicationList(medicalRecordEntity.getMedications());
    medicalRecord.setMedications(medications);
    
    return medicalRecord;
  }
  
  public MedicalRecordEntity medicalRecordToMedicalRecordEntity(MedicalRecord medicalRecord, PersonEntity personEntity) {
    var medicalRecordEntity = new MedicalRecordEntity();
    medicalRecordEntity.setIdPerson(personEntity.getId());

    for (Allergy allergy : medicalRecord.getAllergies()) {
      medicalRecordEntity.addMedicalRecordAllergiesItem(allergyUtils.allergyToMedicalRecordAllergyEntity(allergy.getAllergy()));
    }

    for (Medication medication : medicalRecord.getMedications()) {
      medicalRecordEntity.addMedicalRecordMedicationsItem(medicationUtils.medicationToMedicalRecordMedicationEntity(medication.getMedication()));      
    }
    
    return medicalRecordEntity;
  }
  
  public List<Medication> medicalRecordMedicationEntityListToMedicationList(List<MedicalRecordMedicationEntity> medicalRecordMedicationEntities) {
    List<Medication> medications = new ArrayList<>(); 
    for (MedicalRecordMedicationEntity medicalRecordMedicationEntity : medicalRecordMedicationEntities) {
      medications.add(medicationUtils.medicalRecordMedicationEntityToMedication(medicalRecordMedicationEntity));
    }
    return medications;
  }
  
  public List<Allergy> medicalRecordAllergyEntityListToAllergyList(List<MedicalRecordAllergyEntity> medicalRecordAllergyEntities) {
    List<Allergy> allergies = new ArrayList<>(); 
    for (MedicalRecordAllergyEntity medicalRecordAllergyEntity : medicalRecordAllergyEntities) {
      allergies.add(allergyUtils.medicalRecordAllergyEntityToAllergy(medicalRecordAllergyEntity));
    }
    return allergies;
  }
}
