package io.swagger.business;

import org.springframework.stereotype.Service;
import io.swagger.dao.db.FireStationDao;

@Service
public class FireStationBusiness {
  private final FireStationDao fireStationDao;

  public FireStationBusiness(FireStationDao fireStationDao) {
    this.fireStationDao = fireStationDao;
  }
  

}
