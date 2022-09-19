package io.swagger.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.MedicalRecordEntity;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.MedicalRecord;
import io.swagger.utils.DateUtils;
import io.swagger.utils.MedicalRecordUtils;

@Service
public class MedicalRecordBusiness {
  
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private DateUtils dateUtils;
  @Autowired
  private MedicalRecordUtils medicalRecordUtils;
  
  public MedicalRecord saveMedicalRecord(final MedicalRecord medicalRecord) {
    PersonEntity personEntity = personDao.findPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
    
    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecord.getBirthdate()));
    personDao.update(personEntity);
    
    MedicalRecordEntity medicalRecordEntity = medicalRecordUtils.medicalRecordToMedicalRecordEntity(medicalRecord, personEntity);
    medicalRecordEntity = medicalRecordDao.save(medicalRecordEntity);
    
    return medicalRecordUtils.medicalRecordEntityToMedicalRecord(medicalRecordEntity, personEntity);
  }

  public void deleteMedicalRecord(final String firstName, final String lastName) {
    PersonEntity personEntity = personDao.findPersonByName(firstName, lastName);
    medicalRecordDao.delete(personEntity.getId());
  }

  public MedicalRecord updateMedicalRecord(final MedicalRecord medicalRecord) {
    PersonEntity personEntity = personDao.findPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());
    
    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecord.getBirthdate()));
    personDao.update(personEntity);
    
    MedicalRecordEntity medicalRecordEntity = medicalRecordUtils.medicalRecordToMedicalRecordEntity(medicalRecord, personEntity);
    medicalRecordEntity = medicalRecordDao.update(medicalRecordEntity);
    
    return medicalRecordUtils.medicalRecordEntityToMedicalRecord(medicalRecordEntity, personEntity);
  }

}
