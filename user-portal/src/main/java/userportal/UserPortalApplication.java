package userportal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.RestController;

import userportal.repository.UserMongoRepository;

@SpringBootApplication
@EnableAuthorizationServer
@RestController
@EnableMongoRepositories(basePackageClasses = UserMongoRepository.class)
public class UserPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserPortalApplication.class, args);
	}
	
	 @Bean
	    public ResourceServerTokenServices tokenService() {
	        RemoteTokenServices tokenServices = new RemoteTokenServices();
	        tokenServices.setClientId("tutorialspoint");
	        tokenServices.setClientSecret("my-secret-key");
	        tokenServices.setCheckTokenEndpointUrl("http://localhost:8001/oauth/check_token");
	        return tokenServices;
	    }
	 
	 @Bean
	 public ModelMapper modelMapper(){
	     return new ModelMapper();
	 }
	
}
