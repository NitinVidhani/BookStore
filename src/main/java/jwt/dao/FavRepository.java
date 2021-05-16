package jwt.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import jwt.model.Books;
import jwt.model.Favorites;

public interface FavRepository extends JpaRepository<Favorites, Integer>, CrudRepository<Favorites, Integer>  {

	Favorites save(Optional<Books> book);
	
	@Query("select f from Favorites f where f.userName =:n")
	public List<Favorites> getAllFavByUser(@Param("n") String username);
	
}