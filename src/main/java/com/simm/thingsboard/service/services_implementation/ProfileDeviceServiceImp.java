package com.simm.thingsboard.service.services_implementation;

import java.net.URI;
import java.net.URISyntaxException;

import com.simm.thingsboard.service.device_profiles.DeviceProfileAlarm;
import com.simm.thingsboard.service.util.ToJson;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProfileDeviceServiceImp {
    
    @Value( "${thingsboard.base-path}" )
    private String path;
    
    @Value( "${thingsboard.device-profile.path}" )
    private String deviceProfilePath;

    public DeviceProfileAlarm getDeviceProfile(String token, String id) {

        DeviceProfileAlarm response = new DeviceProfileAlarm();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Authorization", token);

        HttpEntity<DeviceProfileAlarm> request = new HttpEntity<DeviceProfileAlarm>(headers);
        
        String baseUrl = path + deviceProfilePath + "/" + id;

        RestTemplate restTemplate = new RestTemplate();
       
        ResponseEntity<DeviceProfileAlarm> result = restTemplate.exchange(baseUrl, HttpMethod.GET, request, DeviceProfileAlarm.class);
        response = result.getBody();
 
        return response;
        
    }
   
    public DeviceProfileAlarm createProfileDevice(String token, DeviceProfileAlarm deviceProfile) {

        DeviceProfileAlarm response = new DeviceProfileAlarm();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Authorization", token);

        HttpEntity<DeviceProfileAlarm> request = new HttpEntity<DeviceProfileAlarm>(deviceProfile, headers);
        
        String baseUrl = path + deviceProfilePath;

        RestTemplate restTemplate = new RestTemplate();
        
        URI uri = null;

        ToJson.printToJson(deviceProfile);
    
        try {
            uri = new URI(baseUrl);
            ResponseEntity<DeviceProfileAlarm> result = restTemplate.postForEntity(uri, request, DeviceProfileAlarm.class);
            response = result.getBody();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return response;
        
    }

    public int deleteDeviceProfile(String token, String id) {

        int response = 500;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Authorization", token);

        HttpEntity<Void> request = new HttpEntity<Void>(headers);
        
        String baseUrl = path + deviceProfilePath + "/" + id;

        RestTemplate restTemplate = new RestTemplate();
       
        ResponseEntity<Void> result = restTemplate.exchange(baseUrl, HttpMethod.DELETE, request, Void.class);
        response = result.getStatusCode().value();
 
        return response;
        
    }

}
