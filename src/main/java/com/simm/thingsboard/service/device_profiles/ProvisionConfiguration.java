package com.simm.thingsboard.service.device_profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProvisionConfiguration {
    
    private String type;

    private String provisionDeviceSecret;

    public ProvisionConfiguration() {
        this.type = "DISABLED";
    }

}
