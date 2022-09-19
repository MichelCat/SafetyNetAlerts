package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;
import io.swagger.dao.db.PersonDao;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@SpringBootTest
public class PersonBusinessIT {
  
  @Autowired
  private PersonBusiness personBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
  @Autowired
  private PersonDao personDao;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
  }

  // -----------------------------------------------------------------------------------------------
  // Method savePerson
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void savePerson_Normal() {
    // GIVEN
    Person person = MickBoydData.getPerson();
    // WHEN
    assertThat(personBusiness.savePerson(person)).isEqualTo(person);
    // THEN
    assertThat(personDao.findPersonByName(person.getFirstName(), person.getLastName())).isNotNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method updatePerson
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void updatePerson_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");

    Person updatePerson = MickBoydData.getPerson();
    updatePerson.setPhoneNumber("841-874-6514");
    // WHEN
    Person result = personBusiness.updatePerson(updatePerson);
    // THEN
    assertThat(result).isEqualTo(updatePerson);
  }

  // -----------------------------------------------------------------------------------------------
  // Method deletePerson
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void deletePerson_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    personBusiness.deletePerson("Mick", "Boyd");
    // THEN
    assertThat(personDao.findPersonByName("Mick", "Boyd")).isNull();
  }
}
