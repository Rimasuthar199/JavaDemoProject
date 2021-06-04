package userportal.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Document("user_contact_details")
public class UserContactMongo{

	@JsonProperty("contact_no")
	private String contactNo;
}
