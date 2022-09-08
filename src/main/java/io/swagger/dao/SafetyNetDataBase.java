package io.swagger.dao;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import io.swagger.dao.json.entities.SafetyNetJson;
import io.swagger.utils.DateUtils;

@Component
public class SafetyNetDataBase {
  
  @Autowired
  public DateUtils dateUtils;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  // -----------------------------------------------------------------------------------------------
  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() throws StreamReadException, DatabindException, IOException {
    SafetyNetJson safetyNetJson = loadJsonFileInDatabaseService.readFileJson();
    
    dataBasePrepareService.clearDataBase();
    loadJsonFileInDatabaseService.setListPersonEntity(safetyNetJson);
    loadJsonFileInDatabaseService.setListFireStationEntity(safetyNetJson);
    loadJsonFileInDatabaseService.setListAllergyEntity(safetyNetJson);
    loadJsonFileInDatabaseService.setListMedicationEntity(safetyNetJson);
    loadJsonFileInDatabaseService.setListMedicalRecordEntity(safetyNetJson);
  }
}
