package io.swagger.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.MedicalRecord;
import io.swagger.model.Medication;

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

    List<MedicalRecordAllergyEntity> allergies = new ArrayList<>();
    for (Allergy allergy : medicalRecord.getAllergies()) {
      CollectionUtils.addIgnoreNull(allergies
                                  , allergyUtils.allergyToMedicalRecordAllergyEntity(allergy.getAllergy()));      
    }
    medicalRecordEntity.setAllergies(allergies);

    List<MedicalRecordMedicationEntity> medications = new ArrayList<>();
    for (Medication medication : medicalRecord.getMedications()) {
      CollectionUtils.addIgnoreNull(medications
                                  , medicationUtils.medicationToMedicalRecordMedicationEntity(medication.getMedication()));      
    }
    medicalRecordEntity.setMedications(medications);
    
    return medicalRecordEntity;
  }
  
  public List<Medication> medicalRecordMedicationEntityListToMedicationList(List<MedicalRecordMedicationEntity> medicalRecordMedicationEntities) {
    List<Medication> medications = new ArrayList<>(); 
    for (MedicalRecordMedicationEntity medicalRecordMedicationEntity : medicalRecordMedicationEntities) {
      CollectionUtils.addIgnoreNull(medications
          , medicationUtils.medicalRecordMedicationEntityToMedication(medicalRecordMedicationEntity));
    }
    return medications;
  }
  
  public List<Allergy> medicalRecordAllergyEntityListToAllergyList(List<MedicalRecordAllergyEntity> medicalRecordAllergyEntities) {
    List<Allergy> allergies = new ArrayList<>(); 
    for (MedicalRecordAllergyEntity medicalRecordAllergyEntity : medicalRecordAllergyEntities) {
      CollectionUtils.addIgnoreNull(allergies
                      , allergyUtils.medicalRecordAllergyEntityToAllergy(medicalRecordAllergyEntity));
    }
    return allergies;
  }
}
