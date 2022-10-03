package com.safetynet.safetynetalerts.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.safetynet.safetynetalerts.utils.DateUtils;

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
  @Test
  @DisplayName("Null string to date conversion")
  void stringToDateConversion_returnNull() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion(null);
    // THEN
    assertThat(result).isNull();
  }
  
  @Test
  @DisplayName("Convert empty string to date")
  void stringToDateConversion_returnEmpty() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion("");
    // THEN
    assertThat(result).isNull();
  }
  
  @Test
  @DisplayName("Conversion chaîne 01/02/2022 en date")
  void stringToDateConversion_returnDate() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringDDMMYYYYToDateConversion("01/02/2022");
    // THEN
    assertThat(result).isEqualTo(new Date("2022/02/01"));
  }
}
