package com.simm.thingsboard.service.model.device_profile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.simm.thingsboard.service.model.guarantee.Guarantee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deviceProfile")
public class DeviceProfile {

    @Id
	@Column(name = "id", length = (255))
	private String id;

	@NotNull(message = "The name is required")
	@Column(name = "name", nullable = false, length = (255))
	private String name;
    
	@Column(name = "description", nullable = true, length = (500))
	private String description;

	@Column(name = "entityType", nullable = true, length = (100))
	private String entityType;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "guaranteeId")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Guarantee guarantee;

}
