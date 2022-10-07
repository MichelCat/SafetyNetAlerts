package com.safetynet.safetynetalerts.business;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.DataBasePrepareBusiness;
import com.safetynet.safetynetalerts.dao.db.PersonDao;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.utils.PersonUtils;

/**
 * PersonBusinessIT is a class of integration tests on person
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class PersonBusinessIT {
  
  @Autowired
  private PersonBusiness personBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private PersonDao personDao;
  @Autowired
  private PersonUtils personUtils;
  
  private static PersonEntity mickPersonEntity;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    mickPersonEntity = MickBoydData.getPersonEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method savePerson
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Add a person station
   */
  @Test
  void savePerson_Normal() {
    // GIVEN
    // WHEN
    Person result = personBusiness.savePerson(MickBoydData.getPerson());
    // THEN
    assertThat(result).isEqualTo(MickBoydData.getPerson());

    PersonEntity readPersonEntity = personDao.findPersonByName(result.getFirstName(), result.getLastName());
    assertThat(result).isEqualTo(personUtils.conversionPersonEntityToPerson(readPersonEntity));
  }

  // -----------------------------------------------------------------------------------------------
  // Method updatePerson
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Update an existing person
   */
  @Test
  void updatePerson_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);

    Person updatePerson = MickBoydData.getPerson();
    updatePerson.setPhoneNumber("841-874-6514");
    // WHEN
    Person result = personBusiness.updatePerson(updatePerson);
    // THEN
    assertThat(result).isEqualTo(updatePerson);

    PersonEntity readPersonEntity = personDao.findPersonByName(result.getFirstName(), result.getLastName());
    assertThat(result).isEqualTo(personUtils.conversionPersonEntityToPerson(readPersonEntity));
  }

  // -----------------------------------------------------------------------------------------------
  // Method deletePerson
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Delete an person
   */
  @Test
  void deletePerson_Normal() {
    // GIVEN
    personDao.save(mickPersonEntity);
    // WHEN
    assertThat(personBusiness.deletePerson("Mick", "Boyd")).isTrue();
    // THEN
    assertThat(personDao.findPersonByName("Mick", "Boyd")).isNull();
  }
}
