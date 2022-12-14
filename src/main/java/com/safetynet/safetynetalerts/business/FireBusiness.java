package com.safetynet.safetynetalerts.business;

import java.util.ArrayList;
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

/**
 * FireBusiness is the service dealing with residents living at the address
 * 
 * @author MC
 * @version 1.0
 */
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
  private MedicalRecordUtils medicalRecordUtils;

  /**
   * Get the list of inhabitants living at the given address.
   * 
   * @param personAddress Search address
   * @return List of inhabitants living at the given address
   */
  public List<Person> getPersonsLivingInAddress(final String personAddress) {
    List<String> stationAddresses = new ArrayList<>();
    stationAddresses.add(personAddress);
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  /**
   * Get a person's medication
   * 
   * @param idPerson Person ID
   * @return A person's medication list
   */
  public List<Medication> getMedicationByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordMedicationEntityListToMedicationList(medicalRecordDao.findMedicationEntityById(idPerson));
  }

  /**
   * Get a person's allergy
   * 
   * @param idPerson Person ID
   * @return A person's allergy list
   */
  public List<Allergy> getAllergyByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordAllergyEntityListToAllergyList(medicalRecordDao.findAllergyEntityById(idPerson));
  }

  /**
   * Get fire stations serving an address
   * 
   * @param stationAddress Search address
   * @return List of fire stations serving an address
   */
  public List<FireStation> getFireStationByStationAddress(final String stationAddress) {
    List<FireStationEntity> fireStationEntities = fireStationDao.fireStationByStationAddress(stationAddress);
    return fireStationUtils.conversionListFireStationEntityToFireStation(fireStationEntities);
  }
}
