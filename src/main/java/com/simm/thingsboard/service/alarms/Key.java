package com.simm.thingsboard.service.alarms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Key {

    private String type;
    
    private String key;

    public Key() {
        this.type = "TIME_SERIES";
    }

}
