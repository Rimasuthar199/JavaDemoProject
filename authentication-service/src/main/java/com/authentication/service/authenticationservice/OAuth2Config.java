package com.authentication.service.authenticationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration

public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
   private String clientid = "tutorialspoint";
   private String clientSecret = "my-secret-key";
   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n"
   		+ "MIIEowIBAAKCAQEA/g4bpSDE830qZH5Jcv15B7rbMue9+NRf7wrlo3eeKgYw69cq\n"
   		+ "PubODbcRGpvalgd8EuFvI84Yy6IrYfFGBEHmwxUQo6y8DFKiNYyLVhnLEM8Wvh7K\n"
   		+ "qdzWk4cjWLvByCW0WxPUs6c4tcp80+gIF9lWccsT9IPYqbcu77kRTvBNKP8weJCR\n"
   		+ "CT15JfvE2fs8FHw48iRnh92EAyBY2uRTx4L+sMvgcm9JLgf62/92Xjuwl7cefqJQ\n"
   		+ "oIo4HWx0wjK0r+/9yiE/mccVwCft/SLZSaNX/q0sgdZ1ZHvADA2pE99jwNuHcJh6\n"
   		+ "WMU51a2mCBNCxHCINJGNe8Jw7yYv0+aZXiORRwIDAQABAoIBAQCpUIOyGFXKJKfF\n"
   		+ "E7klrrIjqa9+jJ26IOfSqwOCXy0Eut2Nv/dCtpWq5E+82RPjmxdgieeFCEzUIb8h\n"
   		+ "5ieQnM8LQpW1T2CAIe5DG+icuFHyYOG6UVElxa0n4w92iqNKHX7GmD/Qf2i3Fzz8\n"
   		+ "CQ6jvk/zRLGG6vvRl+jrEP7tCg+SerRId240jh4BTopufkkGDqlnWcBa5bFjIqo1\n"
   		+ "ltWpH96ufDE7LlUgB9yfGg9pvkmROfgaHQRxMrHNQCSK6b79XWqvCNfDZJ9pjw+y\n"
   		+ "1mzw8dMGWYl/8okvsf1GtU0g6jmw823kbm3KCYkGGJW4smkoAtIT0psmGzy2qA9d\n"
   		+ "jXhdvztBAoGBAP9cSrlX18gnffOkm74DVB1aIRwWHg+WYXCoGtGz1kmNBFUbMJN6\n"
   		+ "8LC5DqK6FOMMNHiACKCunSe1xtxqaxZyVTXTg1Wx56gmgktO8XiLTe01cKHTwGM3\n"
   		+ "ESpGiMq+ivWNA+Reel+FKeLWLJ1KS6oafE5NnZrfYeCei2AS5DvEwPunAoGBAP6w\n"
   		+ "+q4ql+ms5PbcVkmN8BJqQmXwZYZL7zOMcJ8VjxAOhwgZrO8RI9cpydo/ebC3blxg\n"
   		+ "lNq5h1fCcXL6NDEUsmVICaTV/OrA5ZFp9jpWs/hnUNuIVX6OP5Nex8Zvl4CqDmwx\n"
   		+ "bqFxsAMP1e3QM1+DvrN7uVX1w6SE4dZon4jOhPFhAoGAVhh32SWUyxcFkC/fkqDs\n"
   		+ "qzjIEQAkAn1hPOpwoGNRzFqPK2KPbin1HKBIHvU+NY7X4gjI3+P/eHQBKC95C4hg\n"
   		+ "0lUN4saopDjXlSx4nXvaOi/aWbVbGBho4SgG1tSHZWK1/TkdaTcseB5dzGpQOHnV\n"
   		+ "j1D74kuOZH0P4MR9zuj3Z+ECgYB2W9ijR7gBKA/fiNWwqP2AEVF5/hWwK2mmEwXj\n"
   		+ "aClr1NR7tzLehqCZRRpBCm6K6TOoSlniq4VyCzKdtBi9FlkxKCETMT6v+aS1xwBe\n"
   		+ "bByGu7yfyOOyRHjXC5zbYtCNQnohXXeEH0kkox9PvQmiwZ7F2iFvCR86PWaWNViu\n"
   		+ "QwsVAQKBgCorEqAGfCg7uQ2Q5CTWApvW7+l8Bt9Qk/cjk3gv44hwG2xCuv6TOkCt\n"
   		+ "aVyPkT/gMNMPSVlhBZ1DGEs28PLPjXYilBOoekpjTq3PzzOQxdEEhhVyPmirqwwo\n"
   		+ "Se/IH3qkSrBTM2m+hT4MKrHFg5zeSDXCtWOYkoH5K4uGJf7SluVj\n"
   		+ "-----END RSA PRIVATE KEY-----";
   private String publicKey = "-----BEGIN PUBLIC KEY-----\n"
   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/g4bpSDE830qZH5Jcv15\n"
   		+ "B7rbMue9+NRf7wrlo3eeKgYw69cqPubODbcRGpvalgd8EuFvI84Yy6IrYfFGBEHm\n"
   		+ "wxUQo6y8DFKiNYyLVhnLEM8Wvh7KqdzWk4cjWLvByCW0WxPUs6c4tcp80+gIF9lW\n"
   		+ "ccsT9IPYqbcu77kRTvBNKP8weJCRCT15JfvE2fs8FHw48iRnh92EAyBY2uRTx4L+\n"
   		+ "sMvgcm9JLgf62/92Xjuwl7cefqJQoIo4HWx0wjK0r+/9yiE/mccVwCft/SLZSaNX\n"
   		+ "/q0sgdZ1ZHvADA2pE99jwNuHcJh6WMU51a2mCBNCxHCINJGNe8Jw7yYv0+aZXiOR\n"
   		+ "RwIDAQAB\n"
   		+ "-----END PUBLIC KEY-----";

   @Autowired
   @Qualifier("authenticationManagerBean")
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private BCryptPasswordEncoder passwordEncoder;
   
   @Autowired
	private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;
   
   @Autowired
   UserDetailsServiceImpl userDetailsService;
   
   @Bean
   public JwtAccessTokenConverter tokenEnhancer() {
      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(privateKey);
      converter.setVerifierKey(publicKey);
      return converter;
   }
   @Bean
   public JwtTokenStore tokenStore() {
      return new JwtTokenStore(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//      endpoints.authenticationManager(authenticationManager).pathMapping("/oauth/token", "/oauth/token")
//      .tokenStore(tokenStore())
//     .accessTokenConverter(tokenEnhancer());
      
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
   }
   @Override
   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	   security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
   }
   @Override
   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients.inMemory()
      .withClient(clientid)
      .secret(passwordEncoder.encode(clientSecret))
      .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
      .authorities("ROLE_GUEST", "ROLE_USER", "ROLE_ADMIN")
      .scopes("read", "write").accessTokenValiditySeconds(20000)
         .refreshTokenValiditySeconds(20000).autoApprove(true);

   }
} 
