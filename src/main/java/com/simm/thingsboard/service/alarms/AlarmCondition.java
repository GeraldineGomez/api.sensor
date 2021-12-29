package com.simm.thingsboard.service.alarms;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmCondition {
   
    private List<Condition> condition;

    private Spec spec;

    public AlarmCondition() {
        this.condition = new ArrayList<Condition>();
        this.spec = new Spec();
        
    }
}
