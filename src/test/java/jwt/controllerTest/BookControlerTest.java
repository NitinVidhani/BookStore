package jwt.controllerTest;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class BookControlerTest {
	//MockMvc call
	private MockMvc mockMvc;
	
	//autowiring of webapplicationcontext
	@Autowired
	private WebApplicationContext context;
	
	
	//Setup for Mock mvc with a authorized user before every test case
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	//welcome page 200 status code test case
	@WithMockUser("/user3")
	@Test
	public void welcomePageTest() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/welcomePage").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	assertEquals(200, result.getResponse().getStatus());
	}

	//welcome page 401 status code test case
	@Test
	public void welcomePageTestNegative() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/welcomePage").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andReturn();
	assertEquals(401, result.getResponse().getStatus());
	}
	
	//searchbyid positive testcase
	@WithMockUser("/user3")
	@Test
	public void getBookByIdTest() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/byid/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	assertEquals(200, result.getResponse().getStatus());
	}
	
	//searchbyid negative testcase none existing id given
	@WithMockUser("/user3")
	@Test
	public void getBookByIdTestNegative() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/byid/4000").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andReturn();
	assertEquals(401, result.getResponse().getStatus());
	}
	
	//searchbyid negative testcase for unauthorized user 
	@Test
	public void getBookByIdTestWithoutUserNegative() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/byid/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andReturn();
	assertEquals(401, result.getResponse().getStatus());
	}
	
	//all books positive testcase
	@WithMockUser("/user3")
	@Test
	public void showBooKstest() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/allbooks").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
	assertEquals(200, result.getResponse().getStatus());
		
	}
	//all books negative testcase
	@Test
	public void showBooKstestNegative() throws Exception {
		
	MvcResult result =	mockMvc.perform(get("/allbooks").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andReturn();
	assertEquals(401, result.getResponse().getStatus());
		
	}
	
}