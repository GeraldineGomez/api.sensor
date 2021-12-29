package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {
    
    private double defaultValue;

    private String userValue;

    private String dynamicValue;

}
