package io.swagger.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.FireStationEntity;
import io.swagger.dao.db.entities.PersonEntity;

@Repository
public class PersonDao {

  @Autowired
  private SafetyNetDataBase safetyNetDataBase;
  
  public List<PersonEntity> findPersonByStationNumber(Integer stationNumber) {
//    String stationAddress = safetyNetDataBase.getFireStationEntities().stream()
//            .filter(element -> element.getStation().equals(stationNumber)).findAny().get().getAddress();
//    
//    List<PersonEntity> personEntities = safetyNetDataBase.getPersonEntities().stream()
//        .filter(element -> element.getAddress().equalsIgnoreCase(stationAddress)).collect(Collectors.toList());
    String stationAddress = "";
    for (FireStationEntity fireStationEntity : safetyNetDataBase.getFireStationEntities()) {
      if (fireStationEntity.getStation().equals(stationNumber)) {
        stationAddress = fireStationEntity.getAddress();
        break;
      }
    }

    List<PersonEntity> personEntities = new ArrayList<>();
    for (PersonEntity personEntity : safetyNetDataBase.getPersonEntities()) {
      if (personEntity.getAddress().equals(stationAddress)) {
        personEntities.add(personEntity);
      }
    }
    return personEntities;
  }

  
}
