package jwt.serviceTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jwt.dao.BooksRepository;
import jwt.service.BookService;


@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookServiceTest {

	@MockBean
	private BooksRepository repo;

	@InjectMocks
	private BookService service;

//	@BeforeEach
//	public void setUp() throws Exception {
//		MockitoAnnotations.initMocks(this);
//	}

	// -------------------Testing Repository class------------------------

	@Test
	public void repoTest() {
		BooksRepository repo = Mockito.mock(BooksRepository.class);
		assertNotNull(repo);
	}

	// --------------------Testing Service class------------------------------

	@Test
	public void serviceTest() {
		BookService service = Mockito.mock(BookService.class);
		assertNotNull(service);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

}
