package io.swagger.dao.db.entities;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FireStationEntityTest {

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
  public void getStation_nullStation_returnNull() {
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
  public void setStation_writetation() {
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
  public void getAddress_nullAddress_returnNull() {
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
  public void setAddress_writeAddress() {
    // GIVEN
    // WHEN
    fireStationEntity.setAddress("1509 Culver St");
    // THEN
    assertThat(fireStationEntity.getAddress()).isEqualTo("1509 Culver St");
  }
}
