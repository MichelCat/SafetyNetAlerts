package io.swagger.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import io.swagger.dao.db.entities.PersonEntity;
import io.swagger.data.MickBoydData;
import io.swagger.model.Person;

@SpringBootTest
class PersonUtilsTest {
  
  private static PersonUtils personUtils;
  private static List<PersonEntity> PersonEntities;
  private static List<Person> persons;

  @BeforeEach
  private void setUpPerTest() {
    personUtils = new PersonUtils();
    PersonEntities = new ArrayList<>();
    persons = new ArrayList<>();
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionListPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("")
  void conversionListPersonEntityToPerson_nullPersonEntities_returnNull() {
    // GIVEN
    // WHEN
    List<Person> result = personUtils.conversionListPersonEntityToPerson(PersonEntities);
    // THEN
    assertThat(result).isEqualTo(persons);
  }
  
  @Test
  @DisplayName("")
  void conversionListPersonEntityToPerson_PersonEntity_returnPersons() {
    // GIVEN
    PersonEntity personEntity = MickBoydData.getPersonEntity();
    PersonEntities.add(personEntity);
    
    Person person = MickBoydData.getPerson();
    persons.add(person);
    // WHEN
    List<Person> result = personUtils.conversionListPersonEntityToPerson(PersonEntities);
    // THEN
    assertThat(result).isEqualTo(persons);
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("")
  void conversionPersonEntityToPerson_personEntity_returnPerson() {
    // GIVEN
    PersonEntity personEntity = MickBoydData.getPersonEntity();
    PersonEntities.add(personEntity);
    
    Person person = MickBoydData.getPerson();
    persons.add(person);
    // WHEN
    Person result = personUtils.conversionPersonEntityToPerson(personEntity);
    // THEN
    assertThat(result).isEqualTo(person);
  }
}
