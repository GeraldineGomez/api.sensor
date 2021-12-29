package com.simm.thingsboard.service.model.review;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "The name is required")
	@Column(name = "name", nullable = false, length = (255))
	private String name;

    @NotNull(message = "The type is required")
	@Column(name = "type", nullable = false, length = (255))
	private String type;

	@Column(name = "min", nullable = true)
	private String min;

	@Column(name = "max", nullable = true)
	private String max;

	@Column(name = "value", nullable = true)
	private String value;

	@NotNull(message = "The free is required")
	@Column(name = "free", nullable = false)
	private boolean free;

	@NotNull(message = "The required is required")
	@Column(name = "required", nullable = true)
	private boolean required;

	@NotNull(message = "The parent is required")
	@Column(name = "parent", nullable = true)
	private boolean parent;

	@Column(name = "parentReviewId", nullable = true)
	private Long parentReviewId;

	@Column(name = "deviceProfileAlarmId", nullable = true)
	private String deviceProfileAlarmId;

}
