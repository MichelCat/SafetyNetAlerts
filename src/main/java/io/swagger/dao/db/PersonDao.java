package io.swagger.dao.db;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import io.swagger.dao.db.entities.PersonEntity;

@Repository
public class PersonDao {

  @Autowired
  SafetyNetDataBase safetyNetDataBase;
  
  public List<PersonEntity> findPersonByStationNumber(Integer stationNumber) {
    String stationAddress = safetyNetDataBase.getFireStationEntities().stream()
            .filter(element -> element.getStation().equals(stationNumber)).findAny().get().getAddress();
    
    List<PersonEntity> personEntities = safetyNetDataBase.getPersonEntities().stream()
        .filter(element -> element.getAddress().equalsIgnoreCase(stationAddress)).collect(Collectors.toList());
    
    return personEntities;
  }

  
}
