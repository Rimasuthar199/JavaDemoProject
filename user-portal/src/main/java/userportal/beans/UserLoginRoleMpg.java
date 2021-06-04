package userportal.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_login_role_mpg")
public class UserLoginRoleMpg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_login_role_mpg_id")
	@JsonProperty("id")
	private Integer id;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "user_login_dtl_id")
	private Integer userLoginDetailId;

}
