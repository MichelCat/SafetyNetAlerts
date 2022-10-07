package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.FireStationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.FireStation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.UpdateFireStation;
import com.safetynet.safetynetalerts.utils.FireStationUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

/**
 * FirestationBusiness is the service dealing with fire stations.
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class FirestationBusiness {

  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private FireStationUtils fireStationUtils;

  /**
   * Get the list of inhabitants living at the given address
   * 
   * @param stationNumber Station number
   * @return List of people served by the fire station.
   */
  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  /**
   * Count the number of adults in a list of people
   * 
   * @param persons People object list
   * @return Number of adults
   */
  public int getAdultsLivingIn(List<Person> persons) {
    int numberAdults = 0;
    for (Person person : persons) {
      if (person.getAge() > 18) {
        ++numberAdults;
      }
    }
    return numberAdults;
  }

  /**
   * Count the number of children in a list of people
   * 
   * @param persons People object list
   * @return Number of children
   */
  public int getChildrenLivingIn(List<Person> persons) {
    int numberChildreen = 0;
    for (Person person : persons) {
      if (person.getAge() <= 18) {
        ++numberChildreen;
      }
    }
    return numberChildreen;
  }

  /**
   * Add a new fire station
   * 
   * @param fireStation An object fire station
   * @return FireStation, successful saved
   */
  public FireStation saveFireStation(final FireStation fireStation) {
    FireStationEntity fireStationEntity = fireStationUtils.conversionFireStationToFireStationEntity(fireStation);
    return fireStationUtils.conversionFireStationEntityToFireStation(fireStationDao.save(fireStationEntity));
  }

  /**
   * Update an existing fire station
   * 
   * @param updateFireStation An object fire station
   * @return FireStation, successful updated
   */
  public FireStation updateFireStation(final UpdateFireStation updateFireStation) {
    var oldFireStationEntity = new FireStationEntity();
    oldFireStationEntity.setStation(updateFireStation.getOldStation());
    oldFireStationEntity.setAddress(updateFireStation.getAddress());

    var newFireStationEntity = new FireStationEntity();
    newFireStationEntity.setStation(updateFireStation.getNewStation());
    newFireStationEntity.setAddress(updateFireStation.getAddress());

    FireStationEntity updateFireStationEntity = fireStationDao.update(oldFireStationEntity, newFireStationEntity);
    return fireStationUtils.conversionFireStationEntityToFireStation(updateFireStationEntity);
  }

  /**
   * Delete an fire station
   * 
   * @param stationNumber - The station number of the fire station to delete
   * @param stationAddress - The address of the fire station to delete
   * @return True, successful deleted
   */
  public boolean deleteFireStation(final String stationNumber, final String stationAddress) {
    return fireStationDao.delete(Integer.valueOf(stationNumber), stationAddress);
  }
}
