package com.simm.thingsboard.service.device_profiles;

import java.util.ArrayList;
import java.util.List;

import com.simm.thingsboard.service.alarms.Alarm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileData {

    private Configuration configuration; 
    
    private TransportConfiguration transportConfiguration;

    private ProvisionConfiguration provisionConfiguration; 

    private List<Alarm> alarms;

    public ProfileData() {
        this.configuration = new Configuration();
        this.transportConfiguration = new TransportConfiguration();
        this.provisionConfiguration = new ProvisionConfiguration();
        this.alarms = new ArrayList<Alarm>();
    }

}
