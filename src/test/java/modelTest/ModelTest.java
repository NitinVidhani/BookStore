package modelTest;


import static org.testng.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import jwt.controller.BookController;
import jwt.dao.BooksRepository;
import jwt.model.Books;
import jwt.service.BookService;

public class ModelTest {
	
	@MockBean
	private BooksRepository repo;
	@MockBean
	private BookService service;
	@MockBean
	private BookController control;
	
	
	@Mock
	private Books b;
	
	
	@Test
	public void testBookModelClass() {
		Books b = Mockito.mock(Books.class);
		assertNotNull(b);
	}

	@Test
	public void testServiceClass() {
		BookService service = Mockito.mock(BookService.class);
		assertNotNull(service);
	}

	@Test
	public void testControllerClass() {
		BookController con = Mockito.mock(BookController.class);
		assertNotNull(con);
	}

}
