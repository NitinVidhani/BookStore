package jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.NestedServletException;

import jwt.dao.BooksRepository;
import jwt.dao.FavRepository;
import jwt.model.Books;
import jwt.model.Favorites;
import jwt.service.FavService;


@RestController
public class FavoriteController {

	@Autowired
	private FavRepository frepo;

	@Autowired
	private FavService fservice;
	
	@Autowired
	private BooksRepository repo;

	//Endpoint for adding to favorite list by book id

	/*
	 * @PostMapping("/addtoFav1/{id}") public Favorites updateById1(@PathVariable
	 * int id) { SecurityContext securityContext =
	 * SecurityContextHolder.getContext(); Object principal; String username = null;
	 * if(null != securityContext.getAuthentication()){ principal =
	 * securityContext.getAuthentication().getPrincipal(); username =
	 * securityContext.getAuthentication().getName(); } Optional<Books> booksId
	 * =repo.findById(id); Favorites f=new Favorites();
	 * 
	 * 
	 * f.setTitle(booksId.get().getTitle());
	 * f.setSubtitle(booksId.get().getSubtitle());
	 * f.setIsbn13(booksId.get().getIsbn13()); f.setPrice(booksId.get().getPrice());
	 * f.setImage(booksId.get().getImage()); f.setUrl(booksId.get().getUrl());
	 * f.setUserName(username);
	 * 
	 * return frepo.save(f); }
	 */

	@PostMapping("/addtoFav/{id}")
	public ResponseEntity<?> updateById(@PathVariable int id){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object principal;
		String username = null;
		if(null != securityContext.getAuthentication()){
			principal = securityContext.getAuthentication().getPrincipal();
			username = securityContext.getAuthentication().getName();
		}

		Optional<Books> booksId =repo.findById(id);
		Favorites f=new Favorites();


		f.setTitle(booksId.get().getTitle());
		f.setSubtitle(booksId.get().getSubtitle());
		f.setIsbn13(booksId.get().getIsbn13());
		f.setPrice(booksId.get().getPrice());
		f.setImage(booksId.get().getImage());
		f.setUrl(booksId.get().getUrl());
		f.setUserName(username);

		Favorites fav = frepo.save(f);

		if(repo.findById(id).isPresent())
			return new ResponseEntity<Favorites>(fav, HttpStatus.OK);
		else if(repo.findById(id).isEmpty())
			return new ResponseEntity<Favorites>(fav, HttpStatus.INTERNAL_SERVER_ERROR);

		else {
			return new ResponseEntity<String>("invalid id",HttpStatus.UNAUTHORIZED);
		}
	}
	//Endpoint for getting all the favorites by current session users

	/*
	 * @GetMapping("/myfav1") public List<Favorites> retrieveFavBooksByUser1(){
	 * 
	 * SecurityContext securityContext = SecurityContextHolder.getContext(); Object
	 * principal = null; String username = null; if(null !=
	 * securityContext.getAuthentication()){ principal =
	 * securityContext.getAuthentication().getPrincipal(); username =
	 * securityContext.getAuthentication().getName(); }
	 * 
	 * 
	 * return fservice.getListOfFavByUser(username);
	 * 
	 * 
	 * }
	 */

	@GetMapping("/myfav")
	public ResponseEntity<?> retrieveFavBooksByUser(){
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Object principal = null;
		String username = null;
		if(null != securityContext.getAuthentication()){
			principal = securityContext.getAuthentication().getPrincipal();
			username = securityContext.getAuthentication().getName();
		}
		List<Favorites> fav = fservice.getListOfFavByUser(username);

		if(fav.size()>=0)
			return new ResponseEntity<List<Favorites>>(fav, HttpStatus.OK);

		else {
			return new ResponseEntity<String>("No Books",HttpStatus.UNAUTHORIZED);
		}
	}
	//Endpoint for getting all the favorites of all the users

	/*
	 * @GetMapping("/allfavs1") public List getAllFavBooks1() {
	 * 
	 * return fservice.findAllFavBook(); }
	 */

	@GetMapping("/allfavs")
	public ResponseEntity<?> getAllFavBooks(){
		List<Favorites> fav = fservice.findAllFavBook();
		if(fav.size()>=0)
			return new ResponseEntity<List<Favorites>>(fav, HttpStatus.OK);

		else {
			return new ResponseEntity<String>("No Books",HttpStatus.UNAUTHORIZED);
		}
	}

	//Endpoint to delete the fav from the list

	/*
	 * @DeleteMapping("/deletefav/{id}") public String
	 * deletefavBookFromList(@PathVariable int id) {
	 * 
	 * return fservice.deleteById(id); }
	 */
	@DeleteMapping(value = "/deletefav/{id}")
	public ResponseEntity<?> deletePost(@PathVariable int id) {

		String isRemoved = fservice.deleteById(id);
		
		System.out.println(isRemoved);
		
		if(isRemoved.equalsIgnoreCase("book deleted"))
			return new ResponseEntity<String>("Book Deleted",HttpStatus.OK);
		else if(isRemoved == "book doesn't exists")
			return new ResponseEntity<String>("book doesn't exists",HttpStatus.INTERNAL_SERVER_ERROR);

		else {
			return new ResponseEntity<String>("NOt Working",HttpStatus.UNAUTHORIZED);
		}
	}

}