package com.safetynet.safetynetalerts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.FireStationDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;

@Service
public class DataBasePrepareBusiness {
  
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
