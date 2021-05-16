package jwt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jwt.dao.BooksRepository;
import jwt.model.Books;



@Service
public class BookService {

	
	@Autowired
	private BooksRepository repo;
    private Optional<Books> books;
	
    
    //getBooks function for getting all the books in the store
    
	public List<Books> getBooks()
	{
	
	List<Books> booklist = new ArrayList<>();
	
	String getUrl = "https://api.itbook.store/1.0/new";
	
	RestTemplate restTemplate = new RestTemplate();
	
	String result = restTemplate.getForObject(getUrl, String.class);
	
	JSONObject jsonObj = new JSONObject(result);
	
	JSONArray articlesObj = jsonObj.getJSONArray("books");
	
	for(int i=0; i<articlesObj.length(); i++) {
		Books book = new Books();
		JSONObject element = articlesObj.getJSONObject(i);
		book.setTitle(element.getString("title"));
	    book.setSubtitle(element.getString("subtitle"));
	    book.setIsbn13(element.getString("isbn13"));
	    book.setPrice(element.getString("price"));
	    book.setImage(element.getString("image"));
		book.setUrl(element.getString("url"));
		
		
	
		repo.save(book);
		
		
		booklist.add(book);
	}
	
	return booklist;
	
}

	//getSingleBook function for getting a single book data by the id
	
	public Optional<Books> getsingleBook(int id) {
		// TODO Auto-generated method stub
		return  repo.findById(id);
	
	}
	
	//getSingleBookByTitle function for getting the desired book by providing its title

	public Optional<Books> getsingleBookByTitle(String title) {
		// TODO Auto-generated method stub
		return repo.findByTitle(title);
	}

}
