package userregistration.userregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@RestController
public class UserRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegistrationApplication.class, args);
	}

	 @RequestMapping(value = "/products")
	   public String getProductName() {
	      return "Honey";   
	   }
	 
	 @Bean
	    public ResourceServerTokenServices tokenService() {
	        RemoteTokenServices tokenServices = new RemoteTokenServices();
	        tokenServices.setClientId("tutorialspoint");
	        tokenServices.setClientSecret("my-secret-key");
	        tokenServices.setCheckTokenEndpointUrl("http://localhost:8080/oauth/check_token");
	        return tokenServices;
	    }
}
	

