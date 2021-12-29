package com.simm.thingsboard.service.device_profiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceProfileAlarm {

    private DeviceProfileId id;

    private Long createdTime;

    private TenantId tenantId;

    private String name;

    private String description;

    private String image;

    private String type;

    private String transportType;

    private String provisionType;

    private String defaultRuleChainId;

    private String defaultDashboardId;

    private String defaultQueueName;

    private ProfileData profileData;

    private String provisionDeviceKey;

    private String firmwareId;

    private String softwareId;

    private boolean DEFAULT;

    public DeviceProfileAlarm() {
        this.type = "DEFAULT";
        this.transportType = "DEFAULT";
        this.provisionType = "DISABLED";
        this.profileData = new ProfileData();
        this.DEFAULT = false;
    }
}
