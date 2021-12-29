package com.simm.thingsboard.service.device_profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportConfiguration {
    
    private String type;

    public TransportConfiguration() {
        this.type = "DEFAULT";
    }
}
