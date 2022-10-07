package com.safetynet.safetynetalerts.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.dao.db.entities.PersonEntity;
import com.safetynet.safetynetalerts.data.MickBoydData;
import com.safetynet.safetynetalerts.model.Person;

/**
 * PersonUtilsTest is the unit test class for the Person object conversion utility
 * 
 * @author MC
 * @version 1.0
 */
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
  /**
   * General case test, Conversion PersonEntity list to Person list 
   */
  @Test
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
  
  /**
   * Borderline case test, Conversion null to Person list
   */
  @Test
  void conversionListPersonEntityToPerson_nullPersonEntities_returnNull() {
    // GIVEN
    // WHEN
    List<Person> result = personUtils.conversionListPersonEntityToPerson(PersonEntities);
    // THEN
    assertThat(result).isEqualTo(persons);
  }

  // -----------------------------------------------------------------------------------------------
  // Method conversionPersonEntityToPerson
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Conversion PersonEntity to Person
   */
  @Test
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
