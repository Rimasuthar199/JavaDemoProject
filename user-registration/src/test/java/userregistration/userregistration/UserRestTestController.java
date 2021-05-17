package userregistration.userregistration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import userregistration.userregistration.controller.UserRestController;

@RunWith(SpringRunner.class)
public class UserRestTestController {
	
	@Autowired
	MockMvc mockMvc ;
	
	@Autowired
	private UserRestController userRestController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(userRestController).isNotNull();
	}
	
}
