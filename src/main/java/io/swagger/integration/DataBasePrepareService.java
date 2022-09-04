package io.swagger.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.AllergyDao;
import io.swagger.dao.db.FireStationDao;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.MedicationDao;
import io.swagger.dao.db.PersonDao;

@Service
public class DataBasePrepareService {
  
  @Autowired
  private AllergyDao allergyDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private FireStationDao fireStationDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private PersonDao personDao;
  
  public void clearDataBase() {
    allergyDao.clearTable();
    fireStationDao.clearTable();
    medicalRecordDao.clearTable();
    medicationDao.clearTable();
    personDao.clearTable();
  }

}
