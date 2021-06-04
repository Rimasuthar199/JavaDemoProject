package userportal.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class OAuth2ConfigResourceserver extends ResourceServerConfigurerAdapter {

	@Autowired
	private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

	   private String publicKey = "-----BEGIN PUBLIC KEY-----\n"
		   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/g4bpSDE830qZH5Jcv15\n"
		   		+ "B7rbMue9+NRf7wrlo3eeKgYw69cqPubODbcRGpvalgd8EuFvI84Yy6IrYfFGBEHm\n"
		   		+ "wxUQo6y8DFKiNYyLVhnLEM8Wvh7KqdzWk4cjWLvByCW0WxPUs6c4tcp80+gIF9lW\n"
		   		+ "ccsT9IPYqbcu77kRTvBNKP8weJCRCT15JfvE2fs8FHw48iRnh92EAyBY2uRTx4L+\n"
		   		+ "sMvgcm9JLgf62/92Xjuwl7cefqJQoIo4HWx0wjK0r+/9yiE/mccVwCft/SLZSaNX\n"
		   		+ "/q0sgdZ1ZHvADA2pE99jwNuHcJh6WMU51a2mCBNCxHCINJGNe8Jw7yYv0+aZXiOR\n"
		   		+ "RwIDAQAB\n"
		   		+ "-----END PUBLIC KEY-----";

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/user/findUser").hasAuthority("USER");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore2());
    }

    @Bean
    public TokenStore tokenStore2() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setVerifierKey(publicKey);
        System.out.println("Token  " + tokenConverter.getAccessTokenConverter());
        return tokenConverter;
    }
	
}
