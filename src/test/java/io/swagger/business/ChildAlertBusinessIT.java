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
import io.swagger.model.Person;

@SpringBootTest
public class ChildAlertBusinessIT {
  
  private static List<Person> persons;
  
  @Autowired
  private ChildAlertBusiness childAlertBusiness;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;
  

  @BeforeEach
  private void setUpPerTest() throws Exception {
    dataBasePrepareService.clearDataBase();
    persons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getChildLivingInArea
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getChildLivingInArea_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("YoungBoydData.json");
    // WHEN
    List<Person> result = childAlertBusiness.getChildLivingInArea("1234 Wall Street");
    // THEN
    persons.add(YoungBoydData.getPerson());
    assertThat(result).isEqualTo(persons);
  }
  
  // Borderline cases : Empty list
  @Test
  void getChildLivingInArea_EmptyList() {
    // GIVEN
    // WHEN
    List<Person> result = childAlertBusiness.getChildLivingInArea("1234 Wall Street");
    // THEN
    assertThat(result).isEqualTo(persons);
  }
  
  // -----------------------------------------------------------------------------------------------
  // Method getOtherHouseholdPersons
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getOtherHouseholdPersons_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    List<Person> result = childAlertBusiness.getOtherHouseholdPersons("Young", "Boyd", "1234 Wall Street");
    // THEN
    persons.add(MickBoydData.getPerson());
    assertThat(result).isEqualTo(persons);
  }
  
  // Borderline cases : Empty list
  @Test
  void getOtherHouseholdPersons_EmptyList() {
    // GIVEN
    // WHEN
    List<Person> result = childAlertBusiness.getOtherHouseholdPersons("Young", "Boyd", "1234 Wall Street");
    // THEN
    assertThat(result).isEqualTo(persons);
  }
}
