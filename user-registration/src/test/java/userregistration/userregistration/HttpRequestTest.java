package userregistration.userregistration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TestRestTemplate restTemplate;

	public MvcResult mockResult;

	public ResultActions resultActions;

	@Test
	public void checkHealthAPi() throws Exception {
		this.mockMvc.perform(get("/user/healthCheck")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("OKAY!")));
	}

	@Test
	public void checkUSerSave() throws Exception {
		this.mockMvc.perform(get("/user/healthCheck")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("OKAY!")));
	}

	@Test
	public void saveUserTest() throws Exception {
		String json = "{\"first_name\":\"saf2rgtg\",\"last_name\":\"Suthar\",\"email_id\":\"test.reema@gmail.com\",\"date_of_birth\":\"9898-04-01\",\"joining_date\":\"21-07-2021\",\"user_address\":[{\"city\":\"ttest\",\"state\":\"Gujrat\",\"country\":\"India\",\"zipcode\":\"382418\",\"address_1\":\"addres\",\"address_2\":\"addres\"},{\"city\":\"ttest\",\"state\":\"Gujrat\",\"country\":\"India\",\"zipcode\":\"382418\",\"address_1\":\"addres\",\"address_2\":\"addres\"}],\"user_contact\":[{\"contact_no\":\"7896541234\"},{\"contact_no\":\"7896541234\"}],\"user_login\":{\"username\":\"ReemaSutha\",\"password\":\"testtest\"}}";
		mockResult = mockMvc.perform(post("/user/saveUser").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		String content = mockResult.getResponse().getContentAsString();
		System.out.println("Response " + content);
	}

	@Test
	public void updateUserTest() throws Exception {
		String json = "{\"id\":68,\"created\":\"14-05-2021 10:47:17\",\"modified\":\"14-05-2021 10:47:17\",\"first_name\":\"saf2rgtg\",\"last_name\":\"Suthar\",\"email_id\":\"test.reema@gmail.com\",\"date_of_birth\":\"06-05-0028\",\"joining_date\":\"21-07-2021\",\"active_flag\":true,\"user_address\":[{\"created\":\"14-05-2021 10:47:17\",\"modified\":null,\"city\":\"ttest\",\"state\":\"Gujratdfsfsf\",\"country\":\"India\",\"zipcode\":\"382418\",\"address_1\":\"addres\",\"address_2\":\"addres\"},{\"id\":113,\"created\":\"14-05-2021 10:47:17\",\"modified\":null,\"user_id\":68,\"city\":\"ttest\",\"state\":\"Gujrat\",\"country\":\"India\",\"zipcode\":\"382418\",\"address_1\":\"addres\",\"address_2\":\"addres\"}],\"user_contact\":[{\"id\":112,\"created\":\"14-05-2021 10:47:17\",\"modified\":null,\"user_id\":68,\"contact_no\":\"7896541234\"},{\"id\":113,\"created\":\"14-05-2021 10:47:17\",\"modified\":null,\"user_id\":68,\"contact_no\":\"7896541234\"}],\"user_login\":{\"id\":44,\"created\":\"14-05-2021 10:47:17\",\"modified\":null,\"user_id\":68,\"username\":\"ReemaSutha\",\"password\":\"testtest\"}}";
		mockResult = mockMvc.perform(put("/user/editUser").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();
		String content = mockResult.getResponse().getContentAsString();
		System.out.println("Update Response " + content);
	}

	@Test
	public void searchUserTest() throws Exception {
		resultActions = this.mockMvc.perform(get("/user/search")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("OKAY!")));
		Assert.assertNotNull(resultActions);
	}
}
