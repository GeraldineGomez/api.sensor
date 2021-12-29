package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Condition {

    private Key key;
    
    private String valueType;

    private String value;

    private PredicateAlarm predicate;

    public Condition() {
        this.key = new Key();
        this.valueType = "NUMERIC";
        this.predicate = new PredicateAlarm();
    }
    
}
