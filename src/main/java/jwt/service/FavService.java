package jwt.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwt.dao.FavRepository;
import jwt.model.Books;
import jwt.model.Favorites;


@Service
public class FavService {
	
	@Autowired
	private FavRepository repo;
	
	private Favorites f;


	private List<Books> book;

	public List<Books> getBooks() {
		return book;
	}

	public void setBooks(List<Books> book) {
		this.book = book;
	}

	public String deleteById(int id) {
		// TODO Auto-generated method stub
		if(repo.findById(id)!=null) {
		repo.deleteById(id);
		return "book deleted";
		}
		else {
			return "book doesn't exists";
		}
		
	}

	public List findAllFavBook() {
		// TODO Auto-generated method stub
		 return repo.findAll();
	}

	public List<Favorites> getListOfFavByUser(String username) {
		// TODO Auto-generated method stub
		 return repo.getAllFavByUser(username);
	}


}
