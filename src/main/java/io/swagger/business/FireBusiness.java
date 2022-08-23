package io.swagger.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordAllergyDao;
import io.swagger.dao.db.MedicalRecordMedicationDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.MedicalRecordAllergyEntity;
import io.swagger.dao.db.entities.MedicalRecordMedicationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.utils.PersonUtils;

@Service
public class FireBusiness {
  private final PersonDao personDao;
  private final FireStationDao fireStationDao;
  private final MedicalRecordMedicationDao medicalRecordMedicationDao;
  private final MedicationDao medicationDao;
  private final MedicalRecordAllergyDao medicalRecordAllergyDao;
  private final AllergyDao allergyDao;
  
  @Autowired
  private PersonUtils personUtils;

  public FireBusiness(PersonDao personDao
                    , FireStationDao fireStationDao
                    , MedicalRecordMedicationDao medicalRecordMedicationDao
                    , MedicationDao medicationDao
                    , MedicalRecordAllergyDao medicalRecordAllergyDao
                    , AllergyDao allergyDao) {
    this.personDao = personDao;
    this.fireStationDao = fireStationDao;
    this.medicalRecordMedicationDao = medicalRecordMedicationDao;
    this.medicationDao = medicationDao;
    this.medicalRecordAllergyDao = medicalRecordAllergyDao;
    this.allergyDao = allergyDao;
  }

  public List<Person> getPersonsLivingInAddress(final String personAddress) {
    List<String> stationAddresses = new ArrayList<String>();
    stationAddresses.add(personAddress);
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
  
  public List<Medication> getMedicationByName(final String firstName, final String lastName) {
    List<MedicalRecordMedicationEntity> medicalRecordMedicationEntities = medicalRecordMedicationDao.findMedicationEntityByName(firstName, lastName);
    
    List<Medication> medications = new ArrayList<Medication>(); 
    for (MedicalRecordMedicationEntity medicalRecordMedicationEntity : medicalRecordMedicationEntities) {
      Medication medication = new Medication();
      medication.setMedication(medicationDao.medicationById(medicalRecordMedicationEntity.getIdMedication())
                              + ":"
                              + medicalRecordMedicationEntity.getDosage());
      medications.add(medication);
    }
    return medications;
  }
  
  public List<Allergy> getAllergyByName(final String firstName, final String lastName) {
    List<MedicalRecordAllergyEntity> medicalRecordAllergyEntities = medicalRecordAllergyDao.findAllergyEntityByName(firstName, lastName);
    
    List<Allergy> allergies = new ArrayList<Allergy>(); 
    for (MedicalRecordAllergyEntity medicalRecordAllergyEntity : medicalRecordAllergyEntities) {
      Allergy allergy = new Allergy();
      allergy.setAllergy(allergyDao.allergyById(medicalRecordAllergyEntity.getIdAlergy()));
      allergies.add(allergy);
    }
    return allergies;
  }
  
  public FireStation getFireStationByStationAddress(final String stationAddress) {
    FireStationEntity fireStationEntity = fireStationDao.fireStationByStationAddress(stationAddress);
    
    FireStation fireStation = new FireStation();
    fireStation.setId(fireStationEntity.getStation());
    fireStation.setAddress(fireStationEntity.getAddress());
    return fireStation;
  }
}
