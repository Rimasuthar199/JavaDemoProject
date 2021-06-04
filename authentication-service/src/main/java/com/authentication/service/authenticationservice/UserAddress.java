package com.authentication.service.authenticationservice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_address")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserAddress.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAddress extends BaseBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	@JsonProperty("user_id")
	private User userDetails;

	@Column(name = "city")
	@JsonProperty("city")
	private String city;

	@Column(name = "state")
	@JsonProperty("state")
	private String state;

	@Column(name = "country")
	@JsonProperty("country")
	private String country;

	@Column(name = "zipcode")
	@JsonProperty("zipcode")
	private String zipcode;

	@Column(name = "address_1")
	@JsonProperty("address_1")
	private String address_1;

	@Column(name = "address_2")
	@JsonProperty("address_2")
	private String address_2;

}
