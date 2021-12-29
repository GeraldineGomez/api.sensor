package com.simm.thingsboard.service.model.guarantee;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simm.thingsboard.service.model.device_profile.DeviceProfile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "guarantee")
public class Guarantee {

    @Id
	@Column(name = "id")
	private Long id;

	@NotNull(message = "The name is required")
	@Column(name = "name", nullable = false, length = (255))
	private String name;
    
	@Column(name = "description", nullable = true, length = (500))
	private String description;

	@OneToMany(mappedBy = "guarantee")
	@JsonIgnore
    private List<DeviceProfile> deviceProfiles;
	
}
