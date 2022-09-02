package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FireStationEntityTest {

  private static FireStationEntity fireStationEntity;

  @BeforeEach
  private void setUpPerTest() {
    fireStationEntity = new FireStationEntity();
  }

  // -----------------------------------------------------------------------------------------------
  // Method getStation
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Read null station")
  void getStation_nullStation_returnNull() {
    // GIVEN
    // WHEN
    // THEN
    assertThat(fireStationEntity.getStation()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setStation
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write station")
  void setStation_writetation() {
    // GIVEN
    // WHEN
    fireStationEntity.setStation(1);
    // THEN
    assertThat(fireStationEntity.getStation()).isEqualTo(1);
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
    assertThat(fireStationEntity.getAddress()).isNull();
  }

  // -----------------------------------------------------------------------------------------------
  // Method setAddress
  // -----------------------------------------------------------------------------------------------
  @Test
  @DisplayName("Write address")
  void setAddress_writeAddress() {
    // GIVEN
    // WHEN
    fireStationEntity.setAddress("1509 Culver St");
    // THEN
    assertThat(fireStationEntity.getAddress()).isEqualTo("1509 Culver St");
  }
}
