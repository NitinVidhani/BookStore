package jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dao.BooksRepository;
import jwt.model.Books;
import jwt.service.BookService;

@RestController
@RequestMapping("/")
public class BookController {
	
	@Autowired
	private BookService service;
	
	
	//Welcome Page
	
	@RequestMapping({ "/welcomePage" })
	public ResponseEntity<?> firstPage() {
		String s = "Welcome to Book Store";
		if(s.length()>=0)
			return new ResponseEntity<String>(s,HttpStatus.OK);

		else {
			return new ResponseEntity<String>("NOt Working",HttpStatus.UNAUTHORIZED);
		}
	}
	

	// Endpoint for getting all the books in the store
	
//	@GetMapping("/allbooks")
//	public List<Books> getBookList() {
//		return service.getBooks();	
//	}
	
	//Endpoint for searching the book by its id
	
//	@GetMapping("/byid/{id}")
//	public Optional<Books> getBooksById(@PathVariable int id) {
//		return service.getsingleBook(id);
//	}
//	
	
	@GetMapping("/byid/{id}")
	public ResponseEntity<?> getBooksById(@PathVariable int id){
		Optional<Books> book = service.getsingleBook(id);
		
		if(book.isPresent())
			return new ResponseEntity<Optional>(book,HttpStatus.OK);

		else {
			return new ResponseEntity<String>("No Books",HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
//Endpoint for searching the book by its title
	
//		@GetMapping("/bytitle/{title}")
//		public Optional<Books> getBooksByTitle(@PathVariable String title) {
//			return service.getsingleBookByTitle(title);
//		}
	
	
		
		
		
		@GetMapping("/allbooks")
		public ResponseEntity<?> getBookList(){
			List<Books> books = service.getBooks();
			 	if(books.size()>=0)
						return new ResponseEntity<List<Books>>(books, HttpStatus.OK);

			 else {
				return new ResponseEntity<String>("No Books",HttpStatus.UNAUTHORIZED);
			}
		}
	
		

		
	 
	
	 
}