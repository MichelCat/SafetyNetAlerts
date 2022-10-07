package com.safetynet.safetynetalerts.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.utils.DateUtils;
import com.safetynet.safetynetalerts.utils.MedicalRecordUtils;

/**
 * MedicalRecordBusiness is the service processing medical records.
 * 
 * @author MC
 * @version 1.0
 */
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

  /**
   * Add a new medical record
   * 
   * @param medicalRecord An object medical record
   * @return MedicalRecord, successful saved
   */
  public MedicalRecord saveMedicalRecord(final MedicalRecord medicalRecord) {
    PersonEntity personEntity = personDao.findPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());

    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecord.getBirthdate()));
    personDao.update(personEntity);

    MedicalRecordEntity medicalRecordEntity = medicalRecordUtils.medicalRecordToMedicalRecordEntity(medicalRecord, personEntity);
    medicalRecordEntity = medicalRecordDao.save(medicalRecordEntity);

    return medicalRecordUtils.medicalRecordEntityToMedicalRecord(medicalRecordEntity, personEntity);
  }

  /**
   * Delete an medical record
   * 
   * @param firstName - The first name of the medical record to delete
   * @param lastName - The last name of the medical record to delete
   * @return True, successful deleted
   */
  public boolean deleteMedicalRecord(final String firstName, final String lastName) {
    PersonEntity personEntity = personDao.findPersonByName(firstName, lastName);
    return medicalRecordDao.delete(personEntity.getId());
  }

  /**
   * Update an existing medical record
   * 
   * @param medicalRecord An object medical record
   * @return MedicalRecord, successful updated
   */
  public MedicalRecord updateMedicalRecord(final MedicalRecord medicalRecord) {
    PersonEntity personEntity = personDao.findPersonByName(medicalRecord.getFirstName(), medicalRecord.getLastName());

    personEntity.setBirthdate(dateUtils.stringDDMMYYYYToDateConversion(medicalRecord.getBirthdate()));
    personDao.update(personEntity);

    MedicalRecordEntity medicalRecordEntity = medicalRecordUtils.medicalRecordToMedicalRecordEntity(medicalRecord, personEntity);
    medicalRecordEntity = medicalRecordDao.update(medicalRecordEntity);

    return medicalRecordUtils.medicalRecordEntityToMedicalRecord(medicalRecordEntity, personEntity);
  }
}
