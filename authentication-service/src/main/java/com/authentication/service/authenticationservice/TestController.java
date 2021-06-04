package com.authentication.service.authenticationservice;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
	
	@GetMapping("/health")
	public String healthCheck() {
		return "Working!!";
	}
	
	@GetMapping("/test2")
	public String getUserLogin() {
		
		HashMap<String, String> pathVariables = new HashMap<>();
		pathVariables.put("userName", "nency123");

		
		ResponseEntity<String> response =new RestTemplate().getForEntity("http://localhost:8080/user/finduser/{userName}",
				String.class,
				pathVariables);
		System.out.println(response);
		
		String currencyConversionBean = response.getBody();
		System.out.println(currencyConversionBean);
		
		return currencyConversionBean;
	}

}
