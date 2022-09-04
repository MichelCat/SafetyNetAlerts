package io.swagger.business;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
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
import io.swagger.utils.FireStationUtils;
import io.swagger.utils.PersonUtils;

@Service
public class FireBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private FireStationUtils fireStationUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private AllergyDao allergyDao;

  public List<Person> getPersonsLivingInAddress(final String personAddress) {
    List<String> stationAddresses = new ArrayList<>();
    stationAddresses.add(personAddress);
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
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
  
  public FireStation getFireStationByStationAddress(final String stationAddress) {
    FireStationEntity fireStationEntity = fireStationDao.fireStationByStationAddress(stationAddress);
    return fireStationUtils.conversionFireStationEntityToFireStation(fireStationEntity);
  }
}
