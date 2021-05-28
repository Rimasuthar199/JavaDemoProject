package userregistration.userregistration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BecryptEncoder {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodString = encoder.encode("reema@123");
		System.out.println("Encoded String " + encodString);

	}

}
