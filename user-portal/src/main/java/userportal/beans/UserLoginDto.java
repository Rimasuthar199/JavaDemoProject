package userportal.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("user_id")
	private User userDetails;

	@JsonProperty("username")
	private String userName;

	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("emailid")
	private String emailId;
	
	@JsonProperty("user")
	private User user;

}
