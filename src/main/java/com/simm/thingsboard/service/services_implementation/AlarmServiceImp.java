package com.simm.thingsboard.service.services_implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simm.thingsboard.service.alarms.Alarm;
import com.simm.thingsboard.service.alarms.Condition;
import com.simm.thingsboard.service.alarms.Predicate;
import com.simm.thingsboard.service.device_profiles.DeviceProfileAlarm;
import com.simm.thingsboard.service.model.review.Review;
import com.simm.thingsboard.service.util.ToJson;

@Component
public class AlarmServiceImp {

    @Autowired
    private ProfileDeviceServiceImp deviceProfileImp;

    public Condition defineAlarmCondition(Review review) {
        
        Condition condition = new Condition();
        if(review.getMin() != null && review.getMax() != null) {
        
            condition.getKey().setKey(review.getType());
            
            condition.getPredicate().setType("COMPLEX");
            condition.getPredicate().setOperation("AND");

            Predicate predicateMin =  new Predicate();
            predicateMin.setOperation("GREATER_OR_EQUAL");
            predicateMin.getValue().setDefaultValue(Double.parseDouble(review.getMin()));

            Predicate predicateMax =  new Predicate();
            predicateMax.setOperation("LESS_OR_EQUAL");
            predicateMax.getValue().setDefaultValue(Double.parseDouble(review.getMax()));

            condition.getPredicate().getPredicates().add(predicateMin);
            condition.getPredicate().getPredicates().add(predicateMax);

        }

        return condition;
    }
    
    public Alarm createDeviceProfileAlarm(String token, String id, Alarm alarm) {
        
        DeviceProfileAlarm deviceProfile = deviceProfileImp.getDeviceProfile(token, id);
        deviceProfile.getProfileData().getAlarms().add(alarm);
        ToJson.printToJson(deviceProfile);
        deviceProfileImp.createProfileDevice(token, deviceProfile);
        return alarm; 
    }

    public void updateDeviceProfileAlarm(String token, DeviceProfileAlarm deviceProfile) {
        deviceProfileImp.createProfileDevice(token, deviceProfile);
    }
    
}
