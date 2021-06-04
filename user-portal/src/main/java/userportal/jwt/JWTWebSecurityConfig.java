package userportal.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableWebSecurity

public class JWTWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
	
	 @Autowired
	   private UserDetailsServiceImpl customDetailsService;
	 
	 	@Value("${jwt.get.token.uri}")
		private String authenticationPath;
	 
	   @Bean
	   public PasswordEncoder encoder() {
	      return new BCryptPasswordEncoder();
	   }
	   
	   @Override
	   @Autowired
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(customDetailsService).passwordEncoder(encoder());
	   }
	   @Override
	   protected void configure(HttpSecurity http) throws Exception {
		   http
		   .csrf().disable().exceptionHandling()
			.authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
           .authorizeRequests()
           .antMatchers("/user/findUser").access("hasRole('ADMIN')")
           .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	   }
//	   @Override
//	   public void configure(WebSecurity web) throws Exception {
//		   web.ignoring().antMatchers(HttpMethod.POST, authenticationPath);
//	   }
	   @Override
	   @Bean
	   public AuthenticationManager authenticationManagerBean() throws Exception {
	      return super.authenticationManagerBean();
	   }
	
	   @Bean
	     public DaoAuthenticationProvider authenticationProvider() {
	         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	         authProvider.setUserDetailsService(userDetailsService());
	         authProvider.setPasswordEncoder(encoder());	          
	         return authProvider;
	     }
	   
	   @Override
		public void configure(WebSecurity webSecurity) throws Exception {
			webSecurity.ignoring().antMatchers(HttpMethod.POST, authenticationPath)
					.and().ignoring()
					.antMatchers(HttpMethod.GET, "/" // Other Stuff You want to Ignore
					).and().ignoring()
					.antMatchers("/h2-console/**/**");// Should not be done in Production!
		}
	   
//	   @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//	        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//	        configuration.setAllowCredentials(true);
//	        configuration.addAllowedHeader("Authorization");
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//	        return source;
//	    }
}
