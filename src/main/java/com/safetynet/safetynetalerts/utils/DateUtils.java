package com.safetynet.safetynetalerts.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

/**
 * DateUtils is a date conversion utility class
 * 
 * @author MC
 * @version 1.0
 */
@Service
public class DateUtils {

  /**
   * Convert string format (dd/MM/yyyy) to date
   * 
   * @param stringDate String in (dd/MM/yyyy) format
   * @return Date
   */
  public static Date stringDDMMYYYYToDateConversion(String stringDate) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").parse(stringDate));
    } catch (Exception e) {
    }
    return null;
  }
  
  /**
   * Convert date to string format (dd/MM/yyyy)
   * 
   * @param dateToConvert Date
   * @return String in (dd/MM/yyyy) format
   */
  public static String dateToStringDDMMYYYYConversion(Date dateToConvert) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").format(dateToConvert));
    } catch (Exception e) {
    }
    return null;
  }
}
