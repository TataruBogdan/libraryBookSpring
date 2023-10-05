package be.intecbrussel.libraryappSpring.service;

import be.intecbrussel.libraryappSpring.model.Book;
import be.intecbrussel.libraryappSpring.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    // constructor injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // add a book
    public Optional<Book> addBook(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public Optional<Book> deleteBookById(Long id) {
        Optional<Book> foundBookById = bookRepository.findById(id);
        if (foundBookById.isPresent()) {
            bookRepository.deleteById(id);
            return foundBookById;
        } else {
            return Optional.empty();
        }
    }

    /*public Optional<Book> updateBook(Long id, Book book) {
        return bookRepository.updateBookById(id, book);
    }*/

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book[]> getBookByName(String title) {
        return bookRepository.findByTitleIgnoreCaseContaining(title);
    }

    public Optional<Book[]> getAllAvailableBooks(boolean available) {
        return bookRepository.findAllByIsAvailable(available);
    }

    public Optional<Book[]> getBookByGenre(String genre) {
        return bookRepository.findByGenreIgnoreCaseContaining(genre);
    }
}
