package com.simm.thingsboard.service.services;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.simm.thingsboard.service.device_profiles.DeviceProfileAlarm;
import com.simm.thingsboard.service.model.device_profile.DeviceProfile;
import com.simm.thingsboard.service.model.device_profile.DeviceProfileRepository;
import com.simm.thingsboard.service.model.guarantee.Guarantee;
import com.simm.thingsboard.service.model.guarantee.GuaranteeRepository;
import com.simm.thingsboard.service.services_implementation.ProfileDeviceServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestController
@RequestMapping("profile-device")
public class ProfileDeviceService {
    
    @Autowired
    private GuaranteeRepository guaranteeRepository;

    @Autowired
    private DeviceProfileRepository deviceProfileRepository;

    @Autowired
    private ProfileDeviceServiceImp deviceProfileImp;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceProfileAlarm getDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token, 
         @PathVariable("id") Long id) {
          DeviceProfileAlarm deviceProfileAlarm = null;
          Guarantee guarantee = guaranteeRepository.getById(id);
          if(guarantee != null){
             List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
             if(deviceProfiles != null){
                deviceProfileAlarm = deviceProfileImp.getDeviceProfile(token, deviceProfiles.get(0).getId());
             }
          }
        return deviceProfileAlarm;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceProfileAlarm createDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token, 
      @RequestBody DeviceProfileAlarm deviceProfile) {
        return deviceProfileImp.createProfileDevice(token, deviceProfile);
        
    }
  
    @PostMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceProfileAlarm createDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token, 
      @PathVariable("id") Long id) {

        DeviceProfileAlarm deviceProfile = new DeviceProfileAlarm();

        Guarantee guarantee = guaranteeRepository.findById(id).get();
        
        if(guarantee != null) {
           deviceProfile.setName(guarantee.getName());
           deviceProfile.setDescription(guarantee.getDescription());
           
           deviceProfile = deviceProfileImp.createProfileDevice(token, deviceProfile);

           if(deviceProfile != null) {
              DeviceProfile deviceP = new DeviceProfile();
              deviceP.setId(deviceProfile.getId().getId());
              deviceP.setName(deviceProfile.getName());
              deviceP.setDescription(deviceProfile.getDescription());
              deviceP.setGuarantee(guarantee);
              deviceProfileRepository.save(deviceP);
           }
        }
        
        return deviceProfile;
        
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void putDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token, 
      @PathVariable("id") Long id) {
        DeviceProfileAlarm deviceProfile = new DeviceProfileAlarm();

        Guarantee guarantee = guaranteeRepository.getById(id);
        if(guarantee != null){
           List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
           if (deviceProfiles != null && deviceProfiles.size() > 0) {
             deviceProfile = deviceProfileImp.getDeviceProfile(token, deviceProfiles.get(0).getId());
             deviceProfile.setName(guarantee.getName());
             deviceProfile.setDescription(guarantee.getDescription());
             deviceProfileImp.createProfileDevice(token, deviceProfile);
          }
        }       
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDeviceProfileAlarm(@RequestHeader(name = "X-Authorization") String token, 
      @PathVariable("id") Long id) {
        Guarantee guarantee = guaranteeRepository.getById(id);
        if(guarantee != null){
           List<DeviceProfile> deviceProfiles = deviceProfileRepository.findByGuarantee(guarantee);
           if (deviceProfiles != null && deviceProfiles.size() > 0) {
            deviceProfileRepository.deleteById(deviceProfiles.get(0).getId());
            DeviceProfile deviceProfile = deviceProfileRepository.getById(deviceProfiles.get(0).getId());
            if(deviceProfile != null){
               deviceProfileImp.deleteDeviceProfile(token, deviceProfiles.get(0).getId());
            }
          }
        }       
    }

}
