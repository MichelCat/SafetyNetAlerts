package io.swagger.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class DateUtils {

  public Date stringDDMMYYYYToDateConversion(String stringDate) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").parse(stringDate));
    } catch (Exception e) {
    }
    return null;
  }
  
//  public Date stringCetToDateConversion(String stringDate) {
//    try {
//      return (new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(stringDate));
//    } catch (Exception e) {
//    }
//    return null;
//  }
  
  public String stringDDMMYYYYToCetConversion(String stringDate) {
    try {
      DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
      return (sourceFormat.parse(stringDate).toString());
    } catch (Exception e) {
    }
    return null;
  }
  
  
}
