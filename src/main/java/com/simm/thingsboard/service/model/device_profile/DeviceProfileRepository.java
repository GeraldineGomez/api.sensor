package com.simm.thingsboard.service.model.device_profile;

import java.util.List;

import com.simm.thingsboard.service.model.guarantee.Guarantee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceProfileRepository extends JpaRepository<DeviceProfile, String> { 

    List<DeviceProfile> findByGuarantee(Guarantee guarantee);
    
}
