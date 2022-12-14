package com.safetynet.safetynetalerts.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * DateUtilsTest is the unit test class for the date conversion utility
 * 
 * @author MC
 * @version 1.0
 */
@SpringBootTest
class DateUtilsTest {

  private static DateUtils dateUtils;

  @BeforeEach
  private void setUpPerTest() {
    dateUtils = new DateUtils();
  }

  // -----------------------------------------------------------------------------------------------
  // Method stringToDateConversion
  // -----------------------------------------------------------------------------------------------
  /**
   * General case test, Conversion string 01/02/2022 to date
   */
  @Test
  @DisplayName("Conversion string 01/02/2022 to date")
  void stringToDateConversion_returnDate() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion("01/02/2022");
    // THEN
    assertThat(result).isEqualTo(new Date("2022/02/01"));
  }

  /**
   * Borderline case test, Null string to date conversion
   */
  @Test
  @DisplayName("Null string to date conversion")
  void stringToDateConversion_returnNull() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion(null);
    // THEN
    assertThat(result).isNull();
  }

  /**
   * Borderline case test, Convert empty string to date
   */
  @Test
  @DisplayName("Convert empty string to date")
  void stringToDateConversion_returnEmpty() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion("");
    // THEN
    assertThat(result).isNull();
  }
}
