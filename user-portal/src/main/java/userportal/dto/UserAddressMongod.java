package userportal.dto;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Document("user_address")
public class UserAddressMongod {

	@JsonProperty("city")
	private String city;

	@JsonProperty("state")
	private String state;

	@JsonProperty("country")
	private String country;

	@JsonProperty("zipcode")
	private String zipcode;

	@JsonProperty("address_1")
	private String address_1;

	@JsonProperty("address_2")
	private String address_2;

}
