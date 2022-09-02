package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonEntityTest {

  private static PersonEntity personEntity;
  private static Date todayDate;

  @BeforeEach
  private void setUpPerTest() {
    personEntity = new PersonEntity();
    todayDate = new Date();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null ID person")
  void getId_nullId_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getId()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setId
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write ID person")
  void setId_writeId() {
    // GIVEN
    // WHEN
    personEntity.setId(1);
    // THEN
    assertThat(personEntity.getId()).isEqualTo(1);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getFirstName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null first name")
  void getFirstName_nullFirstName_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getFirstName()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setFirstName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write first name")
  void setFirstName_writeFirstName() {
    // GIVEN
    // WHEN
    personEntity.setFirstName("John");
    // THEN
    assertThat(personEntity.getFirstName()).isEqualTo("John");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getLastName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null last name")
  void getLastName_nullLastName_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getLastName()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setLastName
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write last name")
  void setLastName_writeLastName() {
    // GIVEN
    // WHEN
    personEntity.setLastName("Boyd");
    // THEN
    assertThat(personEntity.getLastName()).isEqualTo("Boyd");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAddress
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null address")
  void getAddress_nullAddress_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getAddress()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setAddress
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write address")
  void setAddress_writeAddress() {
    // GIVEN
    // WHEN
    personEntity.setAddress("1509 Culver St");
    // THEN
    assertThat(personEntity.getAddress()).isEqualTo("1509 Culver St");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getCity
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null city")
  void getCity_nullCity_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getCity()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setCity
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write city")
  void setCity_writeCity() {
    // GIVEN
    // WHEN
    personEntity.setCity("Culver");
    // THEN
    assertThat(personEntity.getCity()).isEqualTo("Culver");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getZip
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null zip")
  void getZip_nullZip_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getZip()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setZip
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write zip")
   void setZip_writeZip() {
    // GIVEN
    // WHEN
    personEntity.setZip("97451");
    // THEN
    assertThat(personEntity.getZip()).isEqualTo("97451");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getPhoneNumber
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null phone number")
  void getPhoneNumber_nullPhoneNumber_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getPhoneNumber()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setPhoneNumber
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write phone number")
  void setPhoneNumber_writePhoneNumber() {
    // GIVEN
    // WHEN
    personEntity.setPhoneNumber("841-874-6512");
    // THEN
    assertThat(personEntity.getPhoneNumber()).isEqualTo("841-874-6512");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getEmail
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null email")
  void getEmail_nullEmail_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getEmail()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setEmail
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write email")
  void setEmail_writeEmail() {
    // GIVEN
    // WHEN
    personEntity.setEmail("jaboyd@email.com");
    // THEN
    assertThat(personEntity.getEmail()).isEqualTo("jaboyd@email.com");
  }

  // -----------------------------------------------------------------------------------------------
  // Method getBirthdate
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null birthdate")
  void getBirthdate_nullBirthdate_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(personEntity.getBirthdate()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setBirthdate
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write birthdate")
  void setBirthdate_writeBirthdate() {
    // GIVEN
    // WHEN
    personEntity.setBirthdate(todayDate);
    // THEN
    assertThat(personEntity.getBirthdate()).isEqualTo(todayDate);
  }

  // -----------------------------------------------------------------------------------------------
  // Method getAge
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Null birthdate, age zero")
  void getAge_nullDate_returnZero() {
    // GIVEN
    // WHEN
    personEntity.setBirthdate(null);
    // THEN
    assertThat(personEntity.getAge()).isZero();
  }

  @Test
  @DisplayName("20 years, return 20")
  void getAge_20Years_return20() {
    // GIVEN
    Calendar c = Calendar.getInstance();
    c.setTime(todayDate);
    c.add(Calendar.YEAR, -20);
    // WHEN
    personEntity.setBirthdate(c.getTime());
    // THEN
    assertThat(personEntity.getAge()).isEqualTo(20);
  }
}
