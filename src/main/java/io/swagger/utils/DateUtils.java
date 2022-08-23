package io.swagger.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class DateUtils {

  public Date stringToDateConversion(String stringDate) {
    try {
      return (new SimpleDateFormat("dd/MM/yyyy").parse(stringDate));
    } catch (Exception e) {
    }
    return null;
  }
}
