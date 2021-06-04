package userportal.dto;

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
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends BaseBean implements Serializable {


	@JsonProperty("id")
	private Integer userId;

	
	@JsonProperty("first_name")
	private String firstName;

	
	@JsonProperty("last_name")
	private String lastName;

	
	@JsonProperty("email_id")
	private String emailId;

	
	@JsonProperty("date_of_birth")
	private Date dob;

	@JsonProperty("joining_date")
	private Date joiningDate;

	@JsonProperty("active_flag")
	private boolean activeFlag;

	@JsonProperty("user_address")
	private  List<UserAddressDto> userAddress;

	@JsonProperty("user_contact")
	private  List<UserContactDto> userContact;

	@JsonProperty("user_login")
	private  UserLoginDto userLogin;
}