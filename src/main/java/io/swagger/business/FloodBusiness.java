package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.FireStation;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.utils.FireStationUtils;
import io.swagger.utils.MedicalRecordUtils;
import io.swagger.utils.PersonUtils;

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
