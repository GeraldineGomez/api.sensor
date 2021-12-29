package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmType {
    
    private AlarmCondition condition;

    private String schedule;
    
    private String alarmDetails;
    
    private String dashboardId;

    public AlarmType() {
        this.condition = new AlarmCondition();
    }
    
}
