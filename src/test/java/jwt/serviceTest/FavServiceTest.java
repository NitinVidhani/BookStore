package jwt.serviceTest;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jwt.dao.BooksRepository;
import jwt.dao.FavRepository;
import jwt.model.Favorites;
import jwt.service.BookService;
import jwt.service.FavService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class FavServiceTest {
	
	@Mock
	private FavRepository repo;
	
	@InjectMocks
	private FavService service;
	
	@MockBean
	 private BooksRepository brepo;
	
	@MockBean
	private BookService bservice;
	
	
//	@Before
//    public void setUp() throws Exception {
//         MockitoAnnotations.initMocks(this);
//    }
	
	
	
	//----------POSITIVE TEST CASE--------------
	
	//----------------DELETE-BY-ID-TEST------------------
	
	@Test
	public void deleteByIdTest() throws InterruptedException{		
		
		String actual = null;
		Optional<Favorites> f1=Optional.ofNullable(new Favorites(1,"java","core java","123456","40","javaa.png","www.javaa.com"));
		Mockito.when(repo.findById(1)).thenReturn(f1);
		
		if( f1!= null)
		{
		repo.deleteById(1);
		actual="book deleted";
		}
		String exp=service.deleteById(1);
		assertEquals(actual,exp);
		   
		 
	}

	//----------NEGATIVE TEST CASE----------------
	
	//----------------DELETE-BY-ID-TEST-NEGATIVE------------------
	
	@Test
	public void deleteByIdTestNegative() throws InterruptedException{		
		
		String actual = null;
		Optional<Favorites> f1=Optional.ofNullable(new Favorites(2,"java","core java","123456","40","javaa.png","www.javaa.com"));
		Mockito.when(repo.findById(1)).thenReturn(f1);
		
		if( f1!= null)
		{
		repo.deleteById(1);
		actual="book deleted";
		}
		else
		{
			actual="book doesn't exists";
		}
		String exp=service.deleteById(1);
		assertEquals(actual,exp);
		   
		 
	}

	//----------POSITIVE TEST CASE----------------
	
	//--------------FIND-ALL-FAV-BOOK-TEST----------------------
	
	
	@Test
	public void findAllFavBookTest() {
		
		Favorites f1=new Favorites(1,"java","core java","123456","40","javaa.png","www.javaa.com");
		Favorites f2=new Favorites(2,"java","core java","123456","40","javaa.png","www.javaa.com");
		
		List<Favorites> favs=new ArrayList<>();
		favs.add(f2);
		favs.add(f1);
		
		Mockito.when(repo.findAll()).thenReturn(favs);
		
		assertEquals(2,service.findAllFavBook().size());
	
	}
	
	//----------NEGATIVE TEST CASE----------------
	
	//--------------FIND-ALL-FAV-BOOK-TEST-NEGATIVE---------------------
	
	@Test
	public void findAllFavBookTestNegative() {
		
		Favorites f1=new Favorites(1,"java","core java","123456","javaa.png","www.javaa.com");
		Favorites f2=new Favorites(2,"java","core java","123456","javaa.png","www.javaa.com");
		
		List<Favorites> favs=new ArrayList<>();
		favs.add(f2);
		favs.add(f1);
		
		Mockito.when(repo.findAll()).thenReturn(favs);
		
		assertNotEquals(3,service.findAllFavBook().size());
	
	}
	
	
	//----------POSITIVE TEST CASE----------------
	
	//--------------GET-LIST-OF-FAV-BY-USER--------------------
	
	
	@Test
	public void getListOfFavByUserTest() {

		Favorites f2=new Favorites(2,"java","core java","123456","javaa.png","www.javaa.com","user2");
		Favorites f3=new Favorites(3,"java","core java","123456","javaa.png","www.javaa.com","user2");
		
		List<Favorites> favs=new ArrayList<>();
		favs.add(f3);
		favs.add(f2);
		
		
		Mockito.when(repo.getAllFavByUser("user2")).thenReturn(favs);
		
		assertEquals(2,service.getListOfFavByUser("user2").size());
	}
	
	
	//----------NEGATIVE TEST CASE----------------
	
	//--------------GET-LIST-OF-FAV-BY-USER-NEGATIVE--------------------
	
	@Test
	public void getListOfFavByUserTestNegative() {

		Favorites f2=new Favorites(2,"java","core java","123456","javaa.png","www.javaa.com","user2");
		Favorites f3=new Favorites(3,"java","core java","123456","javaa.png","www.javaa.com","user1");
		
		List<Favorites> favs=new ArrayList<>();
		favs.add(f3);
		favs.add(f2);
		
		
		Mockito.when(repo.getAllFavByUser("user2")).thenReturn(favs);
		
		assertNotEquals(3,service.getListOfFavByUser("user2").size());
	}
	
	
	
	//----------POSITIVE TEST CASE----------------
	
	
	@Test
	public void findByIdtest() {
		
		Optional<Favorites> f2=Optional.ofNullable(new Favorites(2,"java","core java","123456","javaa.png","www.javaa.com","user2"));
		
		String actual="java";
	    assertEquals(actual,f2.get().getTitle());
	}
	
	@AfterEach
	public void tearDown() throws Exception {
	}


}