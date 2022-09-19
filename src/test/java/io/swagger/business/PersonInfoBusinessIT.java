package io.swagger.business;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.swagger.dao.DataBasePrepareBusiness;
import io.swagger.dao.LoadJsonFileInDatabaseBusiness;
import io.swagger.data.MickBoydData;
import io.swagger.data.YoungBoydData;
import io.swagger.model.Allergy;
import io.swagger.model.Medication;
import io.swagger.model.Person;

@SpringBootTest
public class PersonInfoBusinessIT {
  
  private static List<Person> persons;
  private static List<Medication> medications;
  private static List<Allergy> allergies;
  
  @Autowired
  private PersonInfoBusiness personInfoBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    persons = new ArrayList<>();
    medications = new ArrayList<>();
    allergies = new ArrayList<>();
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllPersonsWithTheSameName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAllPersonsWithTheSameName_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    List<Person> result = personInfoBusiness.getAllPersonsWithTheSameName("Mick", "Boyd");
    // THEN
    persons.add(MickBoydData.getPerson());
    persons.add(YoungBoydData.getPerson());
    assertThat(result).isEqualTo(persons);
  }
  
  // Borderline cases : Empty list
  @Test
  void getAllPersonsWithTheSameName_EmptyList() {
    // GIVEN
    // WHEN
    List<Person> result = personInfoBusiness.getAllPersonsWithTheSameName("Mick", "Boyd");
    // THEN
    assertThat(result).isEqualTo(persons);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getMedicationByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getMedicationByName_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    List<Medication> result = personInfoBusiness.getMedicationByName(1);
    // THEN
    medications.add(MickBoydData.getMedication());
    assertThat(result).isEqualTo(medications);
  }
  
  // Borderline cases : Empty list
  @Test
  void getMedicationByName_EmptyList() {
    // GIVEN
    // WHEN
    List<Medication> result = personInfoBusiness.getMedicationByName(1);
    // THEN
    assertThat(result).isEqualTo(medications);
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getAllergyByName
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getAllergyByName_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    List<Allergy> result = personInfoBusiness.getAllergyByName(1);
    // THEN
    allergies.add(MickBoydData.getAllergy());
    assertThat(result).isEqualTo(allergies);
  }
  
  // Borderline cases : Empty list
  @Test
  void getAllergyByName_EmptyList() {
    // GIVEN
    // WHEN
    List<Allergy> result = personInfoBusiness.getAllergyByName(1);
    // THEN
    assertThat(result).isEqualTo(allergies);
  }
}
