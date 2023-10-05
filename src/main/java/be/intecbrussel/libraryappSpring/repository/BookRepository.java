package be.intecbrussel.libraryappSpring.repository;

import be.intecbrussel.libraryappSpring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book[]> findByTitleIgnoreCaseContaining(String title);
    Optional<Book[]> findAllByIsAvailable(boolean available);

    Optional<Book[]> findByGenreIgnoreCaseContaining(String genre);
}
