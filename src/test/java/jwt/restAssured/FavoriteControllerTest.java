package jwt.restAssured;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jwt.dao.BooksRepository;
import jwt.dao.FavRepository;
import jwt.model.Books;
import jwt.model.Favorites;
import jwt.service.FavService;

@SpringBootTest
public class FavoriteControllerTest {
	//MockMvc call
	private MockMvc mockMvc;
	
	//autowiring of webapplicationcontext
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	private FavRepository fRepo;
	
	@MockBean
	private FavService favService;
	
	@MockBean
	private BooksRepository repo;
	
	//Setup for Mock mvc with a authorized user before every test case
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
		RestAssuredMockMvc.mockMvc(mockMvc);
	}
	
	@Test
	@WithMockUser("/user3")
	public void addtoFav() {
		
		Books book = new Books(1, "Vue.js", "", "1234", "29", "image", "url");
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(fRepo.save(Optional.of(book))).thenReturn(fav);
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(book));
		
		RestAssuredMockMvc
		.given()
		.when()
		.post("http://localhost:8080/addtoFav/{id}", 1)
		.then()
		.assertThat()
		.log()
		.all()
		.statusCode(HttpStatus.SC_OK);

	}
	
	@Test
	public void addtoFavWithoutUser() {
		
		Books book = new Books(1, "Vue.js", "", "1234", "29", "image", "url");
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(fRepo.save(Optional.of(book))).thenReturn(fav);
		Mockito.when(repo.findById(1)).thenReturn(Optional.of(book));
		
		RestAssuredMockMvc
		.given()
		.when()
		.post("http://localhost:8080/addtoFav/{id}", 1)
		.then()
		.assertThat()
		.log()
		.all()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);

	}
	
	@Test
	@WithMockUser("/user3")
	public void myfav() {
		
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(favService.getListOfFavByUser("/user3")).thenReturn(List.of(fav));
	
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/myfav")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("title", hasItem("Vue.js"));
		
	}
	
	@Test
	public void myfavWithoutUser() {
		
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(favService.getListOfFavByUser("/user3")).thenReturn(List.of(fav));
	
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/myfav")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
		
	}
	
	@Test
	@WithMockUser("/user3")
	public void allfavs() {
		
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(favService.findAllFavBook()).thenReturn(List.of(fav));
	
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/allfavs")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body("title", hasItem("Vue.js"));
		
	}
	
	@Test
	public void allfavsWithoutUser() {
		
		Favorites fav = new Favorites(1, "Vue.js", "", "1234", "user", "image", "url");
		
		Mockito.when(favService.findAllFavBook()).thenReturn(List.of(fav));
	
		RestAssuredMockMvc
		.given()
		.when()
		.get("http://localhost:8080/allfavs")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
		
	}
	
	@Test
	@WithMockUser("/user3")
	public void deletefav() {
		
		
		Mockito.when(favService.deleteById(1)).thenReturn("book deleted");
	
		RestAssuredMockMvc
		.given()
		.when()
		.delete("http://localhost:8080/deletefav/{id}", 1)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.body(containsStringIgnoringCase("Book Deleted"));
		
	}
	
	@Test
	@WithMockUser("/user3")
	public void deletefavBookNotExists() {
		
		
		Mockito.when(favService.deleteById(1)).thenReturn("book doesn't exists");
	
		RestAssuredMockMvc
		.given()
		.when()
		.delete("http://localhost:8080/deletefav/{id}", 1)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		
	}
	
	@Test
	public void deletefavWithoutUser() {
				
		Mockito.when(favService.deleteById(1)).thenReturn("Book Deleted");
	
		RestAssuredMockMvc
		.given()
		.when()
		.delete("http://localhost:8080/deletefav/{id}", 1)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
}
