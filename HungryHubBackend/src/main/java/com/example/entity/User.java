package com.example.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 100)
	private String firstName;

	@Column(nullable = false, length = 100)
	private String lastName;

	@Column(nullable = false, length = 100, unique = true)
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false, length = 100)
	private String password;

	private String Role;
	
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	
	@JsonProperty(access = Access.WRITE_ONLY)
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDateTime lastModifiedDate;


}
