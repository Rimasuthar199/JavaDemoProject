package userportal.dto;

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
public class UserLoginRoleMpgDto {
	

	@JsonProperty("id")
	private Integer id;
	
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "user_login_dtl_id")
	private Integer userLoginDetailId;

}
