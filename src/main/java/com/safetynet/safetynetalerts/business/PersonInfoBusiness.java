package com.safetynet.safetynetalerts.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.MedicalRecordUtils;
import com.safetynet.safetynetalerts.utils.PersonUtils;

@Service
public class PersonInfoBusiness {
  
  @Autowired
  private PersonUtils personUtils;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicalRecordUtils medicalRecordUtils;

  public List<Person> getAllPersonsWithTheSameName(final String firstName, final String lastName) {
    List<PersonEntity> personEntities = personDao.findAllPersonsWithTheSameName(firstName, lastName);
    return personUtils.conversionListPersonEntityToPerson(personEntities);
  }
  
  public List<Medication> getMedicationByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordMedicationEntityListToMedicationList(medicalRecordDao.findMedicationEntityById(idPerson));
  }
  
  public List<Allergy> getAllergyByName(final Integer idPerson) {
    return medicalRecordUtils.medicalRecordAllergyEntityListToAllergyList(medicalRecordDao.findAllergyEntityById(idPerson));
  }
}
