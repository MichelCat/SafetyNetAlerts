package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.PersonUtils;

/**
 * PhoneAlertBusiness is the service dealing with phone number to alert.
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class PhoneAlertBusiness {

  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;

  /**
   * Get the phone numbers of residents served by the fire station
   * 
   * @param stationNumber Fire station
   * @return List of telephone numbers served by the fire station
   */
  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
}
