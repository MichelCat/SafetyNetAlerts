package com.safetynet.safetynetalerts.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * SafetyNetDataBase is the component Event Listener for loading the database from a JSON file
 * 
 * @author MC
 * @version 1.0
 */
@Component
public class SafetyNetDataBase {

  @Autowired
  private DataBasePrepareBusiness dataBasePrepareService;
  @Autowired
  private LoadJsonFileInDatabaseBusiness loadJsonFileInDatabaseService;

  /**
   * ContextRefreshedEvent for loading the database from a JSON file
   */
  @EventListener(ContextRefreshedEvent.class)
  public void contextRefreshedEvent() {
    dataBasePrepareService.clearDataBase();
    loadJsonFileInDatabaseService.loadDataBase("data.json");
  }
}
