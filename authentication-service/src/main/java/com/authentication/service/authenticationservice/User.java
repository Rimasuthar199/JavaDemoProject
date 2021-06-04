package com.authentication.service.authenticationservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_basic_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	@JsonProperty("id")
	private Integer userId;

	@Column(name = "first_name")
	@JsonProperty("first_name")
	@NotEmpty(message = "Name is required.")
	@Size(min = 4, max = 30 , message = "name between 4 to 30 character")
	private String firstName;

	@Column(name = "last_name")
	@JsonProperty("last_name")
	@NotEmpty(message = "{Name is required.}")
	@Size(min = 2, max = 30 , message = "name between 4 to 30 character")
	private String lastName;

	@Column(name = "email_id")
	@JsonProperty("email_id")
	@NotEmpty(message = "{EmailId is required.}")
	@Pattern(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
	private String emailId;

	@Column(name = "date_of_birth")
	@JsonProperty("date_of_birth")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;

	@Column(name = "joining_date")
	@JsonProperty("joining_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date joiningDate;

	@Column(name = "active")
	@JsonProperty("active_flag")
	private boolean activeFlag;

	@OneToMany(mappedBy = "userDetails")
	@JsonProperty("user_address")
	private List<UserAddress> userAddress;

	@OneToMany(mappedBy = "userDetails")
	@JsonProperty("user_contact")
	private List<UserContact> userContact;

	@OneToOne(mappedBy = "userDetails", cascade = CascadeType.ALL)
	@JsonProperty("user_login")
	private UserLogin userLogin;
}