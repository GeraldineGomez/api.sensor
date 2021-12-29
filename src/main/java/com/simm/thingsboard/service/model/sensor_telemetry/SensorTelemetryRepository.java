package com.simm.thingsboard.service.model.sensor_telemetry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorTelemetryRepository extends JpaRepository<SensorTelemetry, Long> {
    
}
