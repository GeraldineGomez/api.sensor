package com.simm.thingsboard.service.alarms;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRules {
  
  private AlarmType CRITICAL;

  public CreateRules() {
     this.CRITICAL = new AlarmType();
  }
  
  @JsonProperty("CRITICAL")
  public AlarmType getCRITICAL() {
    return CRITICAL;
  }

  public void setCRITICAL(AlarmType CRITICAL) {
    this.CRITICAL = CRITICAL;
  }
  
}
