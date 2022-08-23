package io.swagger.utils;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DateUtilsTest {

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
  public void stringToDateConversion_returnNull() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringToDateConversion(null);
    // THEN
    assertThat(result).isNull();
  }
  
  @Test
  @DisplayName("Convert empty string to date")
  public void stringToDateConversion_returnEmpty() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringToDateConversion("");
    // THEN
    assertThat(result).isNull();
  }
  
  @Test
  @DisplayName("Conversion chaîne 01/02/2022 en date")
  public void stringToDateConversion_returnDate() {
    // GIVEN
    // WHEN
    Date result = dateUtils.stringToDateConversion("01/02/2022");
    // THEN
    assertThat(result).isEqualTo(new Date("2022/02/01"));
  }
}
