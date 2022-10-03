package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.business.PersonInfoBusiness;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.AllergyDao;
import com.safetynet.safetynetalerts.dao.db.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.db.MedicationDao;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.AllergyEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicalRecordEntity;
import com.safetynet.safetynetalerts.dao.db.entities.MedicationEntity;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.data.YoungBoydData;
import com.safetynet.safetynetalerts.model.Allergy;
import com.safetynet.safetynetalerts.model.Medication;
import com.safetynet.safetynetalerts.model.Person;

@SpringBootTest
class PersonInfoBusinessIT {
  
  @Autowired
  private PersonInfoBusiness personInfoBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private MedicalRecordDao medicalRecordDao;
  @Autowired
  private MedicationDao medicationDao;
  @Autowired
  private AllergyDao allergyDao;
  
  private static PersonEntity mickPersonEntity;
  private static PersonEntity youngPersonEntity;
  private static MedicalRecordEntity mickMedicalRecordEntity;
  private static MedicationEntity mickMedicationEntityAznol;
  private static AllergyEntity mickAllergyEntityNillacilan;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
    youngPersonEntity = YoungBoydData.getPersonEntity();
    mickMedicalRecordEntity = MickBoydData.getMedicalRecordEntity();
    mickMedicationEntityAznol = MickBoydData.getMedicationEntityAznol();
    mickAllergyEntityNillacilan = MickBoydData.getAllergyEntityNillacilan();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllPersonsWithTheSameName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAllPersonsWithTheSameName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    personDao.save(youngPersonEntity);
    // WHEN
    List<Person> result = personInfoBusiness.getAllPersonsWithTheSameName("Mick", "Boyd");
    // THEN
    assertThat(result.contains(MickBoydData.getPerson())).isTrue();
    assertThat(result.contains(YoungBoydData.getPerson())).isTrue();
    assertThat(result.size()).isEqualTo(2);
  }
  
  // Borderline cases : Empty list
  @Test
  void getAllPersonsWithTheSameName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personInfoBusiness.getAllPersonsWithTheSameName("Mick", "Boyd")).isEmpty();
    // THEN
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedicationByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getMedicationByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Medication> result = personInfoBusiness.getMedicationByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getMedicationAznol())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getMedicationByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personInfoBusiness.getMedicationByName(1)).isEmpty();
    // THEN
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllergyByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAllergyByName_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    medicationDao.save(mickMedicationEntityAznol);
    allergyDao.save(mickAllergyEntityNillacilan);
    medicalRecordDao.save(mickMedicalRecordEntity);
    // WHEN
    List<Allergy> result = personInfoBusiness.getAllergyByName(1);
    // THEN
    assertThat(result.contains(MickBoydData.getAllergyNillacilan())).isTrue();
    assertThat(result.size()).isEqualTo(1);
  }
  
  // Borderline cases : Empty list
  @Test
  void getAllergyByName_EmptyList() {
    // GIVEN
    // WHEN
    assertThat(personInfoBusiness.getAllergyByName(1)).isEmpty();
    // THEN
  }
}
