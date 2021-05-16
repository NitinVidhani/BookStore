package jwt.restAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jwt.model.Books;
import jwt.service.BookService;

@SpringBootTest
public class BookControllerTest {
	//MockMvc call
	private MockMvc mockMvc;
	
	//autowiring of webapplicationcontext
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private BookService bookService;
	
	//Setup for Mock mvc with a authorized user before every test case
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		RestAssuredMockMvc.mockMvc(mockMvc);
	}
	
	@Test
	@WithMockUser("/user3")
	public void welcomePage() {
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/welcomePage")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body(containsStringIgnoringCase("welcome"));

	}
	
	@Test
	public void welcomePageTestNegative() {
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/welcomePage")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);

	}
	
	@Test
	@WithMockUser("/user3")
	public void allBooks() {
	
		Mockito.when(bookService.getBooks()).thenReturn(
					List.of(new Books(1, "Vue.js", "", "1234", "29", "image", "url"),
							new Books(2, "Node.js", "", "1234", "29", "image", "url"))
				);
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/allbooks")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("title", hasItem("Vue.js"));

	}
	
	@Test
	public void allBooksNegative() {
	
		Mockito.when(bookService.getBooks()).thenReturn(
					List.of(new Books(1, "Vue.js", "", "1234", "29", "image", "url"),
							new Books(2, "Node.js", "", "1234", "29", "image", "url"))
				);
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/allbooks")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);

	}
	
	@Test
	@WithMockUser("/user3")
	public void byId() {
	
		Mockito.when(bookService.getsingleBook(2)).thenReturn(Optional.of(new Books(2, "Node.js", "", "1234", "29", "image", "url")));
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("/byid/{id}", 2)
		.then()
		.log()
		.all()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("title", containsString("Node.js"));

	}
	
	@Test
	@DisplayName("search book without user auth")
	public void byIdNegative() {
	
		Mockito.when(bookService.getsingleBook(2)).thenReturn(Optional.of(new Books(2, "Node.js", "", "1234", "29", "image", "url")));
		
		RestAssuredMockMvc
		.given()
		.when()
		.get("/byid/{id}", 2)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
}
