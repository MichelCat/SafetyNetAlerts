package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.FireStationUtils;
import com.safetynet.safetynetalerts.utils.MedicalRecordUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

@Service
public class FloodBusiness {
  
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
  private MedicalRecordUtils medicalRecordUtils;

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
  
  public List<Medication> getMedicationByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordMedicationEntityListToMedicationList(medicalRecordDao.findMedicationEntityById(idPerson));
  }
  
  public List<Allergy> getAllergyByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordAllergyEntityListToAllergyList(medicalRecordDao.findAllergyEntityById(idPerson));
  }
  
  public List<FireStation> getFireStationByStationAddress(final String stationAddress) {
    List<FireStationEntity> fireStationEntities = fireStationDao.fireStationByStationAddress(stationAddress);
    return fireStationUtils.conversionListFireStationEntityToFireStation(fireStationEntities);
  }
}
