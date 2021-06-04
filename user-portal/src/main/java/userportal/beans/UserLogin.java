package userportal.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
@Table(name = "user_login_details")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = UserLogin.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLogin extends BaseBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonProperty("id")
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonProperty("user_id")
	private User userDetails;

	@Column(name = "username")
	@JsonProperty("username")
	@NotEmpty(message = "{UserName is required.}")
	@Size(min = 4, max = 30 , message = "name between 4 to 30 character")
	private String userName;

	@Column(name = "password")
	@JsonProperty("password")
	@NotEmpty(message = "{Password is required.}")
	@Size(min = 8, max = 10 , message = "password between 8 to 10 character")
	private String password;
	
	@Column(name = "emailid")
	@JsonProperty("emailid")
	private String emailId;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_login_role_mpg",
            joinColumns = @JoinColumn(name = "user_login_dtl_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    private Set<Role> roles = new HashSet<>();
}