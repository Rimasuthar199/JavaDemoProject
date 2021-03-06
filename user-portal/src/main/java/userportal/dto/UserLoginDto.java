package userportal.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import userportal.beans.User;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLoginDto extends BaseBean implements Serializable {

	
	@JsonProperty("id")
	private Integer id;

	@JsonProperty("username")
	private String userName;

	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("emailid")
	private String emailId;
	
	@JsonProperty("user_id")
	private Integer userId;
	
	@JsonProperty("role")
	Set<RoleDto> role = new HashSet<>();
	
}