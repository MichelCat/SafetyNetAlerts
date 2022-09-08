package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.FireStation;
import io.swagger.model.Person;
import io.swagger.utils.FireStationUtils;
import io.swagger.utils.PersonUtils;

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

  public int getAdultsLivingIn(List<Person> persons, String stationNumber) {
    int numberAdults = 0;
    for (Person person : persons) {
      if (person.getAge() > 18) {
        ++numberAdults;
      }
    }
    return numberAdults;
  }

  public int getChildrenLivingIn(List<Person> persons, String stationNumber) {
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
  
  public FireStation updateFireStation(final FireStation fireStation) {
    FireStationEntity newFireStationEntity = fireStationDao.fireStationByStationAddress(fireStation.getAddress());
    
    fireStationDao.delete(fireStation.getId(), fireStation.getAddress());
    
    newFireStationEntity.setStation(fireStation.getId());
    return fireStationUtils.conversionFireStationEntityToFireStation(fireStationDao.update(newFireStationEntity));
  }

  public void deleteFireStation(final String stationNumber, final String stationAddress) {
    fireStationDao.delete(Integer.valueOf(stationNumber), stationAddress);
  }
}
