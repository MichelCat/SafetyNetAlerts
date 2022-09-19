package io.swagger.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.dao.db.MedicalRecordDao;
import io.swagger.dao.db.PersonDao;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.model.Person;
import io.swagger.utils.MedicalRecordUtils;
import io.swagger.utils.PersonUtils;

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
