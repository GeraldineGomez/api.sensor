package com.simm.thingsboard.service.services;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.simm.thingsboard.service.alarms.Alarm;
import com.simm.thingsboard.service.alarms.Condition;
import com.simm.thingsboard.service.device_profiles.DeviceProfileAlarm;
import com.simm.thingsboard.service.model.device_profile.DeviceProfile;
import com.simm.thingsboard.service.model.device_profile.DeviceProfileRepository;
import com.simm.thingsboard.service.model.guarantee.Guarantee;
import com.simm.thingsboard.service.model.guarantee.GuaranteeRepository;
import com.simm.thingsboard.service.model.review.Review;
import com.simm.thingsboard.service.model.review.ReviewRepository;
import com.simm.thingsboard.service.services_implementation.AlarmServiceImp;
import com.simm.thingsboard.service.services_implementation.ProfileDeviceServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("alarms")
public class AlarmService {

    @Autowired
    private GuaranteeRepository guaranteeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private DeviceProfileRepository deviceProfileRepository;

    @Autowired
    private ProfileDeviceServiceImp deviceProfileImp;

    @Autowired
    private AlarmServiceImp alarmImp;

    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token,
            @PathVariable("id") Long id, @RequestBody Review review) {
        Alarm alarm = new Alarm();
        DeviceProfileAlarm deviceProfileAlarm = null;
        Guarantee guarantee = guaranteeRepository.getById(id);
        if (guarantee != null) {
            System.out.println(guarantee.getName() + "GUARANTEE NAME");
            List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
            System.out.println(deviceProfiles.size() + "DEVICE PROFILE");
            if (deviceProfiles != null && deviceProfiles.size() > 0) {
                deviceProfileAlarm = deviceProfileImp.getDeviceProfile(token, deviceProfiles.get(0).getId());
                System.out.println(deviceProfiles.size() + "DEVICE PROFILE");
                if (deviceProfileAlarm != null) {
                    if (review != null) {
                        alarm.setAlarmType(review.getName());
                        review.setDeviceProfileAlarmId(alarm.getId());
                        System.out.println(deviceProfiles.size() + "DEVICE PROFILE");

                        alarm.getCreateRules().getCRITICAL().getCondition().getCondition().add(alarmImp.defineAlarmCondition(review));

                        alarmImp.createDeviceProfileAlarm(token, deviceProfiles.get(0).getId(), alarm);

                        reviewRepository.save(review);

                    }
                }
            }
        }

        return alarm;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeviceProfileAlarm updateDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token,
            @PathVariable("id") Long id, @RequestBody Review review) {
        DeviceProfileAlarm deviceProfileAlarm = null;
        Guarantee guarantee = guaranteeRepository.getById(id);
        if (guarantee != null) {
            System.out.println(guarantee.getName() + "GUARANTEE NAME");
            List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
            if (deviceProfiles != null && deviceProfiles.size() > 0) {
                System.out.println(deviceProfiles.size() + "DEVICE PROFILE");
                deviceProfileAlarm = deviceProfileImp.getDeviceProfile(token, deviceProfiles.get(0).getId());
                if (deviceProfileAlarm != null) {
                    System.out.println(deviceProfileAlarm.getName() + "DEVICE PROFILE ALARM");
                    if (review != null && deviceProfileAlarm != null && deviceProfileAlarm.getProfileData() != null) {
                        if (deviceProfileAlarm.getProfileData().getAlarms() != null &&
                                deviceProfileAlarm.getProfileData().getAlarms().size() > 0)
                            for (Alarm alarm : deviceProfileAlarm.getProfileData().getAlarms()) {
                                if (alarm.getId().equals(review.getDeviceProfileAlarmId())) {
                                    
                                    System.out.println(alarm.getId() + "DEVICE PROFILE ALARM ID");
                                    System.out.println(review.getDeviceProfileAlarmId() + "DEVICE PROFILE ALARM ID");
                                    alarm.setAlarmType(review.getName());

                                    Condition condition = alarmImp.defineAlarmCondition(review);

                                    alarm.getCreateRules().getCRITICAL().getCondition().getCondition().clear();
                                    alarm.getCreateRules().getCRITICAL().getCondition().getCondition().add(condition);
                                    
                                    alarmImp.updateDeviceProfileAlarm(token, deviceProfileAlarm);
                                    
                                    break;
                                }
                            }

                    }
                }
            }
        }

        return deviceProfileAlarm;
    }


    @DeleteMapping(value = "/{id}/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token,
            @PathVariable("id") Long id,  @PathVariable("reviewId") Long reviewId) {
        System.out.println("IDDD");
        DeviceProfileAlarm deviceProfileAlarm = null;
        Review review = reviewRepository.getById(reviewId);
        Guarantee guarantee = guaranteeRepository.getById(id);
        if (guarantee != null) {
            List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
            if (deviceProfiles != null && deviceProfiles.size() > 0) {
                deviceProfileAlarm = deviceProfileImp.getDeviceProfile(token, deviceProfiles.get(0).getId());
                if (deviceProfileAlarm != null) {
                    if (review != null && deviceProfileAlarm != null && deviceProfileAlarm.getProfileData() != null) {
                        List<Alarm> alarms = deviceProfileAlarm.getProfileData().getAlarms();
                        if (deviceProfileAlarm.getProfileData().getAlarms() != null && alarms.size() > 0)
                            for (int i=0; i < alarms.size(); i++) {
                                if (alarms.get(i).getId().equals(review.getDeviceProfileAlarmId())) {
                                    alarms.remove(i);
                                    deviceProfileAlarm.getProfileData().setAlarms(alarms);
                                    alarmImp.updateDeviceProfileAlarm(token, deviceProfileAlarm);
                                    deviceProfileRepository.deleteById(deviceProfiles.get(0).getId());
                                    break;
                                }
                            }

                    }
                }
            }
        }

    }

}
