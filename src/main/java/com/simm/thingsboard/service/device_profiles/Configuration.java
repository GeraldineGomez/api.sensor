package com.simm.thingsboard.service.device_profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Configuration {

    private String type;

    public Configuration() {
        this.type = "DEFAULT";
    }
    
}
