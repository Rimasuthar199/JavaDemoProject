package userportal.beans;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("user_login_details")
public class UserLoginMongo  {

	@JsonProperty("username")
	private String userName;

	@JsonProperty("password")
	private String password;
	
	@JsonProperty("emailid")
	private String emailId;
	
	/*
	 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 * 
	 * @JoinTable( name = "user_login_role_mpg", joinColumns = @JoinColumn(name =
	 * "user_login_dtl_id"), inverseJoinColumns = @JoinColumn(name = "role_id") )
	 * private Set<Role> roles = new HashSet<>();
	 */
}
