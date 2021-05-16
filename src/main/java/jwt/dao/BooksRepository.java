package jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jwt.model.Books;

public interface BooksRepository extends JpaRepository<Books, Integer>{

	Optional<Books> findByTitle(String title);

}
