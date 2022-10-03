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

  public List<Person> getPersonsLivingNearStation(final String stationNumber) {
    List<String> stationAddresses = fireStationDao.fireStationAddressByStationNumber(Integer.valueOf(stationNumber));
    List<PersonEntity> personEntities = personDao.findPersonByAddresses(stationAddresses);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }

  public int getAdultsLivingIn(List<Person> persons) {
    int numberAdults = 0;
    for (Person person : persons) {
      if (person.getAge() > 18) {
        ++numberAdults;
      }
    }
    return numberAdults;
  }

  public int getChildrenLivingIn(List<Person> persons) {
    int numberChildreen = 0;
    for (Person person : persons) {
      if (person.getAge() <= 18) {
        ++numberChildreen;
      }
    }
    return numberChildreen;
  }
  
  public FireStation saveFireStation(final FireStation fireStation) {
    FireStationEntity fireStationEntity = fireStationUtils.conversionFireStationToFireStationEntity(fireStation);
    return fireStationUtils.conversionFireStationEntityToFireStation(fireStationDao.save(fireStationEntity));
  }
  
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

  public boolean deleteFireStation(final String stationNumber, final String stationAddress) {
    return fireStationDao.delete(Integer.valueOf(stationNumber), stationAddress);
  }
}
