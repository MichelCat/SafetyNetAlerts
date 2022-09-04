package io.swagger.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class PersonInfoBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private AllergyDao allergyDao;

  public List<Person> getAllPersonsWithTheSameName(final String firstName, final String lastName) {
    List<PersonEntity> personEntities = personDao.findAllPersonsWithTheSameName(firstName, lastName);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
  
  public List<Medication> getMedicationByName(final Integer idPerson) {
    List<MedicalRecordMedicationEntity> medicalRecordMedicationEntities = medicalRecordDao.findMedicationEntityById(idPerson);
    
    List<Medication> medications = new ArrayList<>(); 
    for (MedicalRecordMedicationEntity medicalRecordMedicationEntity : medicalRecordMedicationEntities) {
      Medication medication = new Medication();
      medication.setMedication(medicationDao.medicationById(medicalRecordMedicationEntity.getIdMedication())
                              + ":"
                              + medicalRecordMedicationEntity.getDosage());
      medications.add(medication);
    }
    return medications;
  }
  
  public List<Allergy> getAllergyByName(final Integer idPerson) {
    List<MedicalRecordAllergyEntity> medicalRecordAllergyEntities = medicalRecordDao.findAllergyEntityById(idPerson);
    
    List<Allergy> allergies = new ArrayList<>(); 
    for (MedicalRecordAllergyEntity medicalRecordAllergyEntity : medicalRecordAllergyEntities) {
      Allergy allergy = new Allergy();
      allergy.setAllergy(allergyDao.allergyById(medicalRecordAllergyEntity.getIdAlergy()));
      allergies.add(allergy);
    }
    return allergies;
  }

}
