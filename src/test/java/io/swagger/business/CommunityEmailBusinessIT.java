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
import io.swagger.model.Person;

@SpringBootTest
public class CommunityEmailBusinessIT {
  
  private static List<Person> persons;
  
  @Autowired
  private CommunityEmailBusiness communityEmailBusiness;
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
  // Method getPersonByCity
  // -----------------------------------------------------------------------------------------------
  // General case
  @Test
  void getPersonByCity_Normal() {
    // GIVEN
    loadJsonFileInDatabaseService.loadDataBase("MickBoydData.json");
    // WHEN
    List<Person> result = communityEmailBusiness.getPersonByCity("Culver");
    // THEN
    persons.add(MickBoydData.getPerson());
    assertThat(result).isEqualTo(persons);
  }
  
  // Borderline cases : Empty list
  @Test
  void getPersonByCity_EmptyList() {
    // GIVEN
    // WHEN
    List<Person> result = communityEmailBusiness.getPersonByCity("Culver");
    // THEN
    assertThat(result).isEqualTo(persons);
  }
  
  

}
