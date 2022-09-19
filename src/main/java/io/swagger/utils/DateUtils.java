package io.swagger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DateUtils {

  public static Date stringDDMMYYYYToDateConversion(String stringDate) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").parse(stringDate));
    } catch (Exception e) {
    }
    return null;
  }
  
  public static String dateToStringDDMMYYYYConversion(Date dateToConvert) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").format(dateToConvert));
    } catch (Exception e) {
    }
    return null;
  }
}
