package io.swagger.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import io.swagger.utils.DateUtils;

@Component
public class SafetyNetDataBase {
  
  @Autowired
  private DateUtils dateUtils;
  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  // -----------------------------------------------------------------------------------------------
  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
    dataBasePrepareService.clearDataBase();
    loadJsonFileInDatabaseService.loadDataBase("data.json");
  }
}
